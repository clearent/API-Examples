package main

import ( "gateway"
         "fmt"
         "os"
)

func main () {
    url := "https://gateway-sb.clearent.net/rest/v2/transactions"
    apikey := "762ad38136b54570a03be604a5a5a1e5"
    transaction := []byte(`<transaction><type>CAPTURE</type> <amount>200.00</amount> <id>12345</id> </transaction>`)

    resp, err  := gateway.PostTrans(url, apikey, transaction)
    fmt.Errorf("error: %g", err)
    os.Stdout.Write(resp)
}
