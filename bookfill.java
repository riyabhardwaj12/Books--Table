package riya.bhardwaj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class bookfill extends AppCompatActivity {

    private DynamoDBMapper dynamoDBMapper1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookfill);


        // DynamoDBMapper dynamoDBMapper1;
        // AWSMobileClient enables AWS user credentials to access your table
        AWSMobileClient.getInstance().initialize(this).execute();
        AWSCredentialsProvider credentialsProvider = AWSMobileClient.getInstance().getCredentialsProvider();
        AWSConfiguration configuration = AWSMobileClient.getInstance().getConfiguration();

        // Add code to instantiate a AmazonDynamoDBClient
        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(credentialsProvider);

        this.dynamoDBMapper1 = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(configuration)
                .build();


        List<Sample2> l=new ArrayList<>();
        InputStream is=getResources().openRawResource(R.raw.books);
        BufferedReader reader=new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        String line="";
int count=0;
 while(count<10001){
      try{
            while((line=reader.readLine())!=null)
            {
                //Split by ,'s
                String[] tokens=line.split(",");

                //Read the data
                Sample2 ss=new Sample2();
                System.out.println("trying :"+tokens.length);
                if(tokens[0].charAt(tokens[0].length()-1)=='X') {
                    tokens[0] = tokens[0].substring(0, tokens[0].length()-1);
                }
                try {
                    ss.setIsbn(Double.parseDouble(tokens[0]));
                    ss.setOpy(tokens[1]);
                    ss.setRating(tokens[2]);
                    ss.setImage_url(tokens[3]);
                    String tit=tokens[4];
                    System.out.println(tokens[4]+"//"+tokens[5]);
                    int num=5;
                    /*while(num<tokens.length && tokens[num].charAt(0)!=',')
                    {
                        tit+=','+tokens[num];
                        num+=1;
                    }*/
                    ss.setTitle(tit);
                    String auth=tokens[num];

                    for(int kk=num+1;kk<tokens.length;kk++)
                    {
                        auth+=","+tokens[kk];
                    }
                    ss.setAuthor(auth.substring(1));
                    int r = (int) (Math.random() * ( 2000- 100)) + 100;
                    String pp=Integer.toString(r);
                    ss.setPrice(pp);
                    l.add(ss);
                                    }
                catch(Exception e) {
                    System.out.println("Exception : "+e);
                    //continue ala;
                }
                finally {
                   // count+=1;
                }
                //count+=1;
                }
            }
      catch(Exception e){
          System.out.println("ohhh");}
    count+=1;
        }




        System.out.println(l.size());
        System.out.println("runned till here"+l.size());
        final BooksDO Item = new BooksDO();
        for(int i=0;i<l.size();i++)
        {
            final int j=i;
            Item.setISBN(l.get(i).getIsbn());
            Item.setAuthor(l.get(i).getAuthor());
            Item.setBookName(l.get(i).getTitle());
            Item.setPublicationYear(l.get(i).getOpy());
            Item.setImageUrl(l.get(i).getImage_url());
            Item.setRating(l.get(i).getRating());
            Item.setPrice(l.get(i).getPrice());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    dynamoDBMapper1.save(Item);
                    // Item saved
                    System.out.println("hey"+j);
                }
            }).start();
            /*Toast toast=Toast.makeText(getApplicationContext(),"hey"+i,Toast.LENGTH_LONG);
            toast.setMargin(50,50);
            toast.show();*/
            //System.out.println("hey"+i);
        }
        System.out.println("finished");
    }
}
