package com.example.wordpuzzlegame;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.wordpuzzlegame.utils.SmsDeliveredReceiver;
import com.example.wordpuzzlegame.utils.SmsSendReceiver;

import java.util.ArrayList;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

//import java.net.PasswordAuthentication;
//import java.util.Properties;
//import javax.activation.DataSource;
//import javax.mail.Multipart;


public class ForgotPasswordActivity extends AppCompatActivity {

    private Parent parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        parent = new Parent(this).singleParent();



        findViewById(R.id.button_activity_forgot_password_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                sendSMS("+251932151071", "Hello");
//                sendEmail();
//                email();
                SendEmail sendEmail = new SendEmail();
                sendEmail.execute("Hi");
//                SendMailTask sendMailTask = new SendMailTask();
//                sendMailTask.execute()
            }
        });
    }

    private Message email() {
        final String username = "username@gmail.com";
        final String password = "password";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("dawitsileshi45@gmail.com", "Photoshopcs6");
            }
        });
//        Session session = Session.getInstance(props,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(username, password);
//                    }
//                });
        try {
            javax.mail.Message message = new MimeMessage(session);
//            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from-dawitsileshi45@gmail.com"));
            message.setRecipients(javax.mail.Message.RecipientType.TO,
                    InternetAddress.parse("to-dawitsileshi45@gmail.com"));
            message.setSubject("Forgotten Password");
            message.setText("Dear, " + parent.getParent_name() + ". Here is your password"
                    + "\n\n <b>" + parent.getParent_password() + "</b>");

            MimeBodyPart messageBodyPart = new MimeBodyPart();

            javax.mail.Multipart multipart = new MimeMultipart();

            messageBodyPart = new MimeBodyPart();
            String file = "path of file to be attached";
            String fileName = "attachmentName";
            javax.activation.DataSource source = new FileDataSource(file);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            System.out.println("Done");
            return message;

//            SendMailTask sendMailTask = new SendMailTask();
//            sendMailTask.execute(message);
//            Transport.send(message);


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
//        return null;
    }
        protected void sendEmail() {
            Log.i("Send email", "");

            String[] TO = {"someone@gmail.com"};
            String[] CC = {"xyz@gmail.com"};
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setDataAndType(Uri.parse("mailto:"), "text/plain");
//            emailIntent.setType("text/plain");
//            emailIntent.setData(Uri.parse("mailto:"));


            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
            emailIntent.putExtra(Intent.EXTRA_CC, CC);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

            try {
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                finish();
//                Log.i("Finished sending email...", "");
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(ForgotPasswordActivity.this,
                        "There is no email client installed.", Toast.LENGTH_SHORT).show();
            }
        }

    private void sendSMS(String phoneNumber, String message) {
        ArrayList<PendingIntent> sentPendingIntents = new ArrayList<PendingIntent>();
        ArrayList<PendingIntent> deliveredPendingIntents = new ArrayList<PendingIntent>();
        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
                new Intent(this, SmsSendReceiver.class), 0);
        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
                new Intent(this, SmsDeliveredReceiver.class), 0);
        try {
            SmsManager sms = SmsManager.getDefault();
            ArrayList<String> mSMSMessage = sms.divideMessage(message);
            for (int i = 0; i < mSMSMessage.size(); i++) {
                sentPendingIntents.add(i, sentPI);
                deliveredPendingIntents.add(i, deliveredPI);
            }
            sms.sendMultipartTextMessage(phoneNumber, null, mSMSMessage,
                    sentPendingIntents, deliveredPendingIntents);

        } catch (Exception e) {

            e.printStackTrace();
            Toast.makeText(getBaseContext(), "SMS sending failed...",Toast.LENGTH_SHORT).show();
        }

    }

    public class SendEmail extends AsyncTask<String, Integer, Integer> {

        ProgressDialog progressDialog;
        private StringBuilder all_email;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            progressDialog = new ProgressDialog(ForgotPasswordActivity.this);
//            progressDialog.setMessage("Uploading, please wait...");
//            progressDialog.show();
//            if (selecteduser_arr != null) {
//                all_email = new StringBuilder();
//                for (int i = 0; i < selecteduser_arr.size(); i++) {
//                    if (i == 0) {
//                        all_email.append(selecteduser_arr.get(i));
//                    } else {
//                        String temp = "," + selecteduser_arr.get(i);
//                        all_email.append(temp);
//                    }
//                }
//            }
        }

        @Override
        protected Integer doInBackground(String... strings) {

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication("dawitsileshi45@gmail.com", "photoshopcs6");
                        }
                    });

            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("dawitsileshi45@gmail.com"));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse("dawitsileshi45@gmail.com"));
                message.setSubject("Testing Subject");
                message.setText("Dear Mail Crawler," +
                        "\n\n No spam to my email, please!");

                Transport.send(message);

                System.out.println("Done");

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            return 1;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
//            progressDialog.dismiss();
        }
    }

//public static class SendMailTask extends AsyncTask<Message, Void, Void>
//{
//    private ProgressDialog progressDialog;
//
//    @Override
//    protected void onPreExecute()
//    {
//        super.onPreExecute();
//        progressDialog = ProgressDialog.show(ForgotPasswordActivity.this, "Please wait", "Sending mail", true, false);
//    }
//
//    @Override
//    protected void onPostExecute(Void aVoid)
//    {
//        super.onPostExecute(aVoid);
//        progressDialog.dismiss();
//    }
//
//    protected Void doInBackground(javax.mail.Message... messages)
//    {
//        try
//        {
//            Transport.send(messages[0]);
//        } catch (MessagingException e)
//        {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
}



