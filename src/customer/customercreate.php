<?php include '../config/config.php';?>
<?php

function parseArray($data) {
    if (is_null($data)){
        $html = "no data to parse";
    } else {
        global $trans_id;
        $html = "";
        $html .= "<ul>";
        foreach ( $data as $key => $value ) :
            $html .= "<li>";
            if (is_array ( $value )) {
                $html .= "<b>" . $key . "</b> : " . parseArray ( $value );
            } else if ($key == "href") {
                $html .= "<b>" . $key . "</b> : <a href=\"request_detail.php?id=" . $trans_id . "\" target=\"request_detail\" >" . $trans_id . " - " . $value . "</a>";
            } else {
                if ($key == "id") {
                    $trans_id = $value;
                }
                $html .= "<b>" . $key . "</b> : " . $value;
            }
            $html .= "</li>";
        endforeach;
        $html .= "</ul>";
    }
    return $html;
}

// Set the API URI
$uri = "https://gateway-sb.clearent.net/rest/v2/customers";
$trans_id=0;

$headers = array (
        "Accept: " . ACCEPT,
        "Content-Type: " . CONTENT_TYPE,
        "Cache-Control: no-cache",
        "api-key: " . API_KEY
);
// customer detail variables
$email = $_POST ['email'];
$phone = $_POST ['phone'];
$comments = $_POST ['comments'];
$firstname = $_POST ['firstname'];
$lastname = $_POST ['lastname'];
//billing address
$firstnamebill = $_POST ['firstnamebill'];
$lastnamebill = $_POST ['lastnamebill'];
$companybill = $_POST ['companybill'];
$streetbill = $_POST ['streetbill'];
$street2bill = $_POST ['street2bill'];
$citybill = $_POST ['citybill'];
$zipbill = $_POST ['zipbill'];
$countrybill = $_POST ['countrybill'];
$phonebill = $_POST ['phonebill'];
//shipping address
$firstnameship = $_POST ['firstnameship'];
$lastnameship = $_POST ['lastnameship'];
$companyship = $_POST ['companyship'];
$streetship = $_POST ['streetship'];
$street2ship = $_POST ['street2ship'];
$cityship = $_POST ['cityship'];
$zipship = $_POST ['zipship'];
$countryship = $_POST ['countryship'];
$phoneship = $_POST ['phoneship'];

$billingaddress = array (
        "first-name" => $firstnamebill,
        "last-name" => $lastnamebill,
        "company" => $companybill,
        "street" => $streetbill,
        "street2" => $street2bill,
        "city" => $citybill,
        "zip" => $zipbill,
        "country" => $countrybill,
        "phone" => $phonebill,
);

$shippingaddress = array (
        "first-name" => $firstnameship,
        "last-name" => $lastnameship,
        "company" => $companyship,
        "street" => $streetship,
        "street2" => $street2ship,
        "city" => $cityship,
        "zip" => $zipship,
        "country" => $countryship,
        "phone" => $phoneship,
);

// Build the PHP associative array "$customerDetails" that contains the customer elements as well as the nested billing and shipping addresses
$customerDetails = array (
        "email" => $email,
        "phone" => $phone,
        "comments" => $comments,
        "first-name" => $firstname,
        "last-name" => $lastname,
        "billing-address" => $billingaddress,
        "shipping-address" => $shippingaddress
);
// Convert the PHP associative array to JSON
$body = json_encode ( $customerDetails );
$headers_encoded = json_encode ( $headers );
$ch = curl_init ();

// set the url
curl_setopt ( $ch, CURLOPT_URL, $uri );
// set the headers
curl_setopt ( $ch, CURLOPT_HTTPHEADER, $headers );
// set the body
curl_setopt ( $ch, CURLOPT_POSTFIELDS, $body );
// return full response if curl_exec is successful
curl_setopt ( $ch, CURLOPT_RETURNTRANSFER, 1 );

// ignore ssl for our test
curl_setopt( $ch, CURLOPT_SSL_VERIFYPEER, false );

// execute request
$response = curl_exec ( $ch );

$curl_error = "";
if(curl_errno($ch)){
    $curl_error .= 'Curl error: ' . curl_error($ch);
}

// Create the PHP associative array "$results" from the JSON response
$results = json_decode ( $response, true );


?>
<style type="text/css">
td.half {
    width: 50%;
}
td.half iframe{
    border:0px;
    height:100%;
    width:100%;
}
</style>
<table>

    <tr>
        <td colspan="2">
            <hr> curl errors<hr>
            <?= $curl_error ?>
        </td>
    </tr>

    <tr>
        <td colspan="2">
            <hr> headers<hr>
            <?= $headers_encoded ?>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <hr> Request details<hr>
            <?= $body?>
        </td>
    </tr>

    <tr>
        <td colspan="2">
            <hr> Full Response<hr>
            <?= $response?>
        </td>
    </tr>
    <tr>
        <td class="half">
            <hr> Parsed Response<hr>
            <!-- Display the transaction results as an HTML unordered list by iterating over the associative array $results -->
            <?= parseArray($results)?>
        </td>
        <td class="half">
            <iframe src="about:blank" name="request detail" id="request_detail"></iframe>
        </td>
    </tr>
</table>
