Imports System.Diagnostics
Imports System.Net.Http
Imports System.Net.Http.Headers
Imports System.Threading.Tasks

Namespace VBGatewayAPIDemo
    Public Structure config
        Public Const ApiKey As String = "api key goes here"
        Public Const JsonMediaType As String = "application/json"
        Public Const BaseUri As String = "https://gateway-sb.clearent.net"
        Public Const TransactionUri As String = "rest/v2/transactions"
    End Structure

    Class Gateway
        Private Shared isInitialized = False
        Public Shared Sub Main()
            Dim client = New HttpClient()
            client.BaseAddress = New Uri(config.BaseUri)
            client.DefaultRequestHeaders.Accept.Clear()
            client.DefaultRequestHeaders.Accept.Add(New MediaTypeWithQualityHeaderValue(config.JsonMediaType))
            client.DefaultRequestHeaders.Add("api-key", config.ApiKey)

            Dim transaction As String
            Dim response As Task(Of String)

            InitializeConnection(client).Wait()

            ' sale
            transaction = "{""type"":""SALE"",""card"":""4111111111111111"", ""exp-date"":""1218"",""amount"":""23.00""}"
            response = RunTransaction(client, transaction)
            Debug.WriteLine("response = " + response.Result)

            ' authorization
            transaction = "{""type"":""AUTH"",""card"":""4111111111111111"", ""exp-date"":""1219"",""amount"":""25.00""}"
            response = RunTransaction(client, transaction)
            Debug.WriteLine("response = " + response.Result)

            ' capture
            transaction = "{""type"":""CAPTURE"",""amount"":""25.00"",""id"":""12345""}"
            response = RunTransaction(client, transaction)
            Debug.WriteLine("response = " + response.Result)

            ' forced sale
            transaction = "{""type"":""FORCED SALE"",""card"":""4111111111111111"", ""exp-date"":""1219"",""amount"":""25.00"",""order-id"":""000123456"",""authorization-code"":""APPC10""}"
            response = RunTransaction(client, transaction)
            Debug.WriteLine("response = " + response.Result)

            ' refund
            transaction = "{""type"":""REFUND"",""amount"":""25.00"",""id"":""12345""}"
            response = RunTransaction(client, transaction)
            Debug.WriteLine("response = " + response.Result)

            ' void
            transaction = "{""type"":""VOID"",""id"":""12345""}"
            response = RunTransaction(client, transaction)
            Debug.WriteLine("response = " + response.Result)

        End Sub

        Shared Async Function InitializeConnection(client As HttpClient) As Task(Of String)
            If Not isInitialized Then
                Await client.GetAsync(config.BaseUri)
                Debug.WriteLine("connection initialized")
                isInitialized = True
            End If
        End Function


        Shared Async Function RunTransaction(client As HttpClient, transaction As String) As Task(Of String)
            ' examine our request body
            Debug.WriteLine(New String("-"c, 40))
            Debug.WriteLine(Convert.ToString("transaction = ") & transaction)

            ' convert this to http content
            Dim content = New StringContent(transaction)
            content.Headers.ContentType = New MediaTypeWithQualityHeaderValue("application/json")

            Dim response As HttpResponseMessage = client.PostAsync(config.TransactionUri, content).Result

            Return Await response.Content.ReadAsStringAsync()

        End Function

    End Class
End Namespace
