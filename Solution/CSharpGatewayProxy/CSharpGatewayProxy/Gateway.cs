using System;
using System.Diagnostics;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading.Tasks;

namespace CSharpGatewayAPIDemo
{
    public struct config
    {
        public const string ApiKey = "api key goes here";
        public const string JsonMediaType = "application/json";
        public const string BaseUri = "https://gateway-sb.clearent.net";
        public const string TransactionUri = "rest/v2/transactions";
    }

    class Gateway
    {
        static void Main()
        {
            var client = new HttpClient();
            client.BaseAddress = new Uri(config.BaseUri);
            client.DefaultRequestHeaders.Accept.Clear();
            client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue(config.JsonMediaType));
            client.DefaultRequestHeaders.Add("api-key", config.ApiKey);

            string transaction;

            // sale
            transaction = "{\"type\":\"SALE\",\"card\":\"4111111111111111\", \"exp-date\":\"1219\",\"amount\":\"25.00\"}";
            RunTransaction(client, transaction).Wait();
            
            // authorization
            transaction = "{\"type\":\"AUTH\",\"card\":\"4111111111111111\", \"exp-date\":\"1219\",\"amount\":\"25.00\"}";
            RunTransaction(client, transaction).Wait();
            
            // capture
            transaction = "{\"type\":\"CAPTURE\",\"amount\":\"25.00\",\"id\":\"12345\"}";
            RunTransaction(client, transaction).Wait();
            
            // forced sale
            transaction = "{\"type\":\"FORCED SALE\",\"card\":\"4111111111111111\", \"exp-date\":\"1219\",\"amount\":\"25.00\",\"order-id\":\"000123456\",\"authorization-code\":\"APPC10\"}";
            RunTransaction(client, transaction).Wait();
            
            // refund
            transaction = "{\"type\":\"REFUND\",\"amount\":\"25.00\",\"id\":\"12345\"}";
            RunTransaction(client, transaction).Wait();
            
            // void
            transaction = "{\"type\":\"VOID\",\"id\":\"12345\"}";
            RunTransaction(client, transaction).Wait();
            
        }

        static async Task RunTransaction(HttpClient client, string transaction)
        {
            // examine our request body
            Debug.WriteLine(new string('-', 40));
            Debug.WriteLine("sale = " + transaction);
            
            // convert this to http content
            var content = new StringContent(transaction);
            content.Headers.ContentType = new MediaTypeWithQualityHeaderValue("application/json");
            
            // send our request and wait for response
            HttpResponseMessage response = await client.PostAsync(config.TransactionUri, content);
                               
            // examine the response
            Debug.WriteLine("response = " + response.Content.ReadAsStringAsync().Result);
        }
    }
}