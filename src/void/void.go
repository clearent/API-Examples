package main

import ( "gateway"
         "fmt"
         "os"
)

func main () {
    url := "https://gateway-sb.clearent.net/rest/v2/transactions"
    apikey := "api key goes here"
    transaction := []byte(`<transaction><type>VOID</type> <id>12345</id> </transaction>`)

    resp, err  := gateway.PostTrans(url, apikey, transaction)
    fmt.Errorf("error: %g", err)
    os.Stdout.Write(resp)
}
