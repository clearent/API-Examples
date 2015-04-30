package gateway

import ( "os"
         "fmt"
         "net/http"
         "encoding/xml"
         "io/ioutil"
         "bytes"
)

type Transaction struct {
    XMLName	xml.Name	`xml:"response"`
    Api_key	string	`xml:"api_key, omitempty, omitempty"`
    Transtype	string	`xml:"type, omitempty"`
    Amount float32	`xml:"amount, omitempty"`
    Csc string	`xml:"csc, omitempty"`
    Exp_date int	`xml:"exp_date, omitempty"`
    Tip_amount float32	`xml:"tip_amount, omitempty"`
    Id	string	`xml:"id, omitempty"`
    Card int	`xml:"card, omitempty"`
    Invoice	int	`xml:"invoice, omitempty"`
    Purchase_order	int	`xml:"purchase_order, omitempty"`
    Email	string	`xml:"email, omitempty"`
    Customer_id	int	`xml:"customer_id, omitempty"`
    Order_number	int	`xml:"customer_id, omitempty"`
    Description	string	`xml:"description, omitempty"`
    Comments	string	`xml:"comments, omitempty"`
    Client_ip	string	`xml:"client_ip, omitempty"`
    First_name	string	`xml:"billing>first_name, omitempty"`
    Last_name	string	`xml:"billing>last_name, omitempty"`
    Company	string	`xml:"billing>company, omitempty"`
    Street	string	`xml:"billing>street, omitempty"`
    Street2	string	`xml:"billing>street2, omitempty"`
    City	string	`xml:"billing>city, omitempty"`
    State	string	`xml:"billing>state, omitempty"`
    Zip		int		`xml:"billing>zip, omitempty"`
    Country	string	`xml:"billing>country, omitempty"`
    Phone	int		`xml:"billing>phone, omitempty"`
    Billing_is_shipping	bool	`xml:"billing_is_shipping, omitempty"`
    Ship_first_name	string	`xml:"shipping>first_name, omitempty"`
    Ship_last_name	string	`xml:"shipping>last_name, omitempty"`
    Ship_company	string	`xml:"shipping>company, omitempty"`
    Ship_street	string	`xml:"shipping>street, omitempty"`
    Ship_street2	string	`xml:"shipping>street2, omitempty"`
    Ship_city	string	`xml:"shipping>city, omitempty"`
    Ship_state	string	`xml:"shipping>state, omitempty"`
    Ship_zip		int		`xml:"shipping>zip, omitempty"`
    Ship_country	string	`xml:"shipping>country, omitempty"`
    Ship_phone	int		`xml:"shipping>phone, omitempty"`
    Result string	`xml:"result, omitempty"`
    Display_message string	`xml:"display_message, omitempty"`
    Transid	int	`xml:"transid, omitempty"`
    Result_code int	`xml:"result_code, omitempty"`
    Avs_result_code string	`xml:"avs_result_code, omitempty"`
    Csc_result_code string	`xml:"csc_result_code, omitempty"`
    Error_code int	`xml:"error_code, omitempty"`
    Batch_number int	`xml:"batch_number, omitempty"`
}

func PostTrans (url string, apikey string, transaction []byte) ([]byte, error) { //posts transactions as xml to gateway
     os.Stdout.Write(transaction)
     fmt.Printf("\n")
     buf := bytes.NewBuffer(transaction)
     cli := &http.Client{}
    req, err := http.NewRequest("POST", url, buf)
    req.Header.Add("api_key", apikey)
    req.Header.Add("Content-Type", "application/xml")
    req.Header.Add("Accept", "application/xml")
    resp, err := cli.Do(req)
        if err != nil {
        fmt.Println("Error! Return values are", err)
        }
    data, _ := ioutil.ReadAll(resp.Body)
    var r Transaction
    xml.Unmarshal(data, &r)
    fmt.Println(r.Amount)
    defer resp.Body.Close()
        return data, err
}
func GetTrans (url string, apikey string, filter string, value string) (*http.Response, error) { //gets xml responses from gateway and parses
    url = url + "?" + filter + "=" + value
    fmt.Printf(url)
    req, err := http.NewRequest("GET", url, nil)
    req.Header.Add("api_key", apikey)
    req.Header.Add("Content-Type", "application/xml")
    req.Header.Add("Accept", "application/xml")
    cli := &http.Client{}
    resp, err := cli.Do(req)
    fmt.Printf("Response: %v", resp.Status)
    data, err := ioutil.ReadAll(resp.Body)
    if err != nil {
          fmt.Print("fatal")
                   }
    var f Transaction
    xml.Unmarshal(data, &f)
    fmt.Printf("Id: %v", f.Transid)
    defer resp.Body.Close()
    return resp, err
}

