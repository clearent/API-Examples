package main

import ( "gateway"
         "fmt"
         "os"
)

func main () {
    url := "https://gateway-sb.clearent.net/rest/v2/transactions"
    apikey := "api key goes here"
    transaction := []byte(`<transaction><type>REFUND</type> <amount>200.00</amount> <card>4111111111111111</card> <id>12345</id> </transaction>`)

    resp, err  := gateway.PostTrans(url, apikey, transaction)
    fmt.Errorf("error: %g", err)
    os.Stdout.Write(resp)
}
