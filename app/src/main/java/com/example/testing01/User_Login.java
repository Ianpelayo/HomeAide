package com.example.testing01;
import android.content.DialogInterface;
        import android.content.Intent;
        import android.os.Bundle;
        import android.text.TextUtils;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.ProgressBar;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AlertDialog;
        import androidx.appcompat.app.AppCompatActivity;

        import com.google.android.gms.auth.api.signin.GoogleSignIn;
        import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
        import com.google.android.gms.auth.api.signin.GoogleSignInClient;
        import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
        import com.google.android.gms.common.api.ApiException;
        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.OnFailureListener;
        import com.google.android.gms.tasks.OnSuccessListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;


        public class User_Login extends AppCompatActivity {

            GoogleSignInOptions gso;
            GoogleSignInClient gsc;
            ImageView googleBtn, backBtn;

            EditText EEmail, EPassword;
            Button ELoginBtn;
            TextView ECreateNewBtn,EForgotPassword;
            FirebaseAuth FAuth;
            ProgressBar progressBar;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_user_login);

                googleBtn = findViewById(R.id.google_btn);

                gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
                gsc = GoogleSignIn.getClient(this,gso);


                EEmail = findViewById(R.id.emailaddress);
                EPassword = findViewById(R.id.password);
                FAuth = FirebaseAuth.getInstance();
                progressBar = findViewById(R.id.progressBar2);
                ELoginBtn = findViewById(R.id.Loginbtn);

                EForgotPassword = findViewById(R.id.ForgotPassword);
                ECreateNewBtn = findViewById(R.id.CreateBtn);



                ELoginBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String email = EEmail.getText().toString().trim();
                        String password = EPassword.getText().toString().trim();

                        if (TextUtils.isEmpty(email)) {
                            EEmail.setError("Email is Required");
                            return;
                        }
                        if (TextUtils.isEmpty(password)) {
                            EPassword.setError("Password is Required");
                            return;
                        }
                        if (password.length() < 6) {
                            EPassword.setError("Password must be greater than 6 Characters");
                        }

                        FAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(User_Login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), OnBoardingActivity.class));
                                }else{
                                    Toast.makeText(User_Login.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
                    }
                });

                    ECreateNewBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getApplicationContext(), Registration.class));
                    }
                });

                backBtn = findViewById(R.id.backBtn);

                ImageView backBtn = (ImageView) findViewById(R.id.backBtn);

                backBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getApplicationContext(), Login_as.class));
                    }
                });



                EForgotPassword.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditText resetMail = new EditText(view.getContext());
                        AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
                        passwordResetDialog.setTitle("Reset Password");
                        passwordResetDialog.setMessage(" Enter your Email to Received the Reset link");
                        passwordResetDialog.setView(resetMail);
                        passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                String mail = resetMail.getText().toString();
                                FAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(User_Login.this, " Rest link sent to your email", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(User_Login.this, "Error! Reset link is not sent" + e.getMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        });

                        passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        passwordResetDialog.create().show();
                    }
                });


                GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
                if(acct!=null){
                    navigateToSecondActivity();
                }

                googleBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        signIn();
                    }
                });
            }

            void signIn(){
                Intent signInIntent = gsc.getSignInIntent();
                startActivityForResult(signInIntent,1000);
            }


            @Override
            protected void onActivityResult(int requestCode, int resultCode,Intent data) {
                super.onActivityResult(requestCode, resultCode, data);
                if(requestCode == 1000){
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

                    try {
                        task.getResult(ApiException.class);
                        navigateToSecondActivity();
                    } catch (ApiException e) {
                        Toast.makeText(getApplicationContext(), " Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }

            }
            void navigateToSecondActivity(){
                finish();
                Intent intent = new Intent(User_Login.this,OnBoardingActivity.class);
                startActivity(intent);
            }


        }