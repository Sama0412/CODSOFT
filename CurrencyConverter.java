import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter Base Currency (Example: USD, INR, EUR): ");
            String base = sc.next().toUpperCase();

            System.out.print("Enter Target Currency: ");
            String target = sc.next().toUpperCase();

            System.out.print("Enter Amount: ");
            double amount = sc.nextDouble();

            String apiURL = "https://api.exchangerate-api.com/v4/latest/" + base;

            URL url = new URL(apiURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));

            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            String data = response.toString();

            // Simple rate extract (basic method)
            String search = "\"" + target + "\":";
            int index = data.indexOf(search);

            if (index == -1) {
                System.out.println("Currency not found!");
                return;
            }

            int start = index + search.length();
            int end = data.indexOf(",", start);

            double rate = Double.parseDouble(data.substring(start, end));

            double convertedAmount = amount * rate;

            System.out.println("\n===== RESULT =====");
            System.out.println(amount + " " + base + " = " + convertedAmount + " " + target);

        } catch (Exception e) {
            System.out.println("Error fetching currency data.");
        }

        sc.close();
    }
}
