// Java Program to Showcase When to use ForkJoinPool vs ExecutorService

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
/*
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
*/
// Class 1
// helper class implementing ForkJoinPool
class ForkJoinPoolTest {

    // ManagedBlocker and fetches Page for provided in
    // constructor inside block() method of the interface.

    // The result is stored in private variable pageBytes
    // which is returned by method getPage()

    // Method 1
    // TO fetch data from page
    public static class FetchPage implements ForkJoinPool.ManagedBlocker {
        private String url;
        private ExecutorService executorSerivce;
        private byte[] pageBytes;
        private static ConcurrentHashMap<String, byte[]> pagesMap = new ConcurrentHashMap<>();

        public FetchPage(String url,ExecutorService executorSerivce)
        {
            this.url = url;
            this.executorSerivce = executorSerivce;
        }

        @Override
        // It also start a compensatory thread while current
        // thread is blocked in this method.
        public boolean block() throws InterruptedException
        {
            if ((pageBytes = pagesMap.get(url)) != null) {
                return true;
            }

            Callable<byte[]> callable = new Callable<byte[]>() {
                public byte[] call() throws Exception
                {
                   /* CloseableHttpClient client
                            = HttpClients.createDefault();
                    HttpGet request
                            = new HttpGet(url);
                    CloseableHttpResponse response
                            = client.execute(request);
                    return EntityUtils.toByteArray(
                            response.getEntity());*/
                    return new byte[] {};
                }
            };

            Future<byte[]> future = executorSerivce.submit(callable);

            // Try block to check for exceptions
            try {
                pageBytes = future.get();
            }catch (InterruptedException | ExecutionException e) {
                pageBytes = null;
            }
            return true;
        }

        // Method 3
        // Returning true if result is ready and
        // There is no need to call block() method.
        @Override public boolean isReleasable()
        {
            if (pageBytes != null) {
                return true;
            }
            return false;
        }

        // Method 4
        public byte[] getPage() { return pageBytes; }
    }

    // Class 2
    // Static class
    static class MyRecursiveTask extends RecursiveTask<String> {

        // This class implements RecurciveTask which fetches
        // page for the URL specified in constructor by
        // calling FetchPage class using
        // ForkJoinPool.managedBlock() method and calculates
        // SHA sum for the content of the page.
        private String url;
        private ExecutorService executorSerivce;

        // Method 1
        public MyRecursiveTask(String url, ExecutorService executorSerivce)
        {
            this.url = url;
            this.executorSerivce = executorSerivce;
        }

        // Method 2
        protected String compute()
        {

            // Try block to check for exceptions
            try {

                FetchPage fp = new FetchPage(url, executorSerivce);
                ForkJoinPool.managedBlock(fp);
                byte[] bytes = fp.getPage();
                if (bytes != null) {
                    String code = toHexString(getSHA(bytes));
                    hashPageMap.put(url, code);
                    return code;
                }
            }

            // Handling exceptions
            catch (InterruptedException | NoSuchAlgorithmException e) {
                return null;
            }
            return null;
        }
    }

    // Method 3
    private static ConcurrentHashMap<String, String> hashPageMap = new ConcurrentHashMap<>();

    // Method 4
    // Main driver method
    public static void main(String[] args)
    {
        ExecutorService executorSerivce = Executors.newFixedThreadPool(50);
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        MyRecursiveTask task1 = new MyRecursiveTask("https://www.yahoo.com", executorSerivce);
        MyRecursiveTask task2 = new MyRecursiveTask("https://www.google.com", executorSerivce);

        Future<String> f1 = forkJoinPool.submit(task1);
        Future<String> f2 = forkJoinPool.submit(task2);

        try {
            String res1 = f1.get();
            String res2 = f2.get();
            System.out.println("URL:https://www.yahoo.com SHAsum:" + res1);
            System.out.println("URL:https://www.yahoo.com SHAsum:" + res2);
            executorSerivce.shutdown();
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    // Method 5
    // to calculate SHA sum for input byte[] and
    // return result as byte array
    public static byte[] getSHA(byte[] input) throws NoSuchAlgorithmException
    {
        // Static getInstance method is called with hashing
        // SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return md.digest(input);
    }

    // Method 6
    // To converts input byte[] to hexadecimal
    // representation.
    public static String toHexString(byte[] hash)
    {
        // Converting byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Converting message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Padding with leading zeros
        // using left shift operator
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }
}
