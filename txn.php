<?php

function parseArray($data) {
    global $trans_id;
    $html = "";
    $html .= "<ul>";
    foreach ( $data as $key => $value ) :
    $html .= "<li>";
    if (is_array ( $value )) {
        $html .= "<b>" . $key . "</b> : " . parseArray ( $value );
    } else if ($key == "href") {
        $html .= "<b>" . $key . "</b> : <a href=\"tran_detail.php?id=" . $trans_id . "\" target=\"tran_detail\" >" . $trans_id . " - " . $value . "</a>";
    } else {
        if ($key == "id") {
            $trans_id = $value;
        }
        $html .= "<b>" . $key . "</b> : " . $value;
    }
    $html .= "</li>";
    endforeach;
    $html .= "</ul>";

    return $html;
}

// Set the API URI
$uri = "https://gateway-sb.clearent.net/rest/v2/transactions";
$trans_id=0;
$contenttype = "application/json";
$apikey = "12fa1a5617464354a72b3c9eb92d4f3b";

$headers = array (
        "Accept: " . $contenttype,
        "Content-Type: " . $contenttype,
        "Cache-Control: no-cache",
        "api-key: " . $apikey
);

$trantype = $_POST ['trantype'];
$amount = $_POST ['amount'];
// make sure card number only contains numbers (remove any non-digits)
$cardnum = preg_replace ( "/[^0-9,.]/", "", $_POST ['cardnum'] );
$expdate = $_POST ['expdate'];

// Build the PHP associative array "$txnDetails" that contains the transaction elements
$txnDetails = array (
        "type" => $trantype,
        "amount" => $amount,
        "card" => $cardnum,
        "exp-date" => $expdate
);
// Convert the PHP associative array to JSON
$body = json_encode ( $txnDetails );

$ch = curl_init ();

// set the url
curl_setopt ( $ch, CURLOPT_URL, $uri );
// set the headers
curl_setopt ( $ch, CURLOPT_HTTPHEADER, $headers );
// set the body
curl_setopt ( $ch, CURLOPT_POSTFIELDS, $body );
// return full response if curl_exec is successful
curl_setopt ( $ch, CURLOPT_RETURNTRANSFER, 1 );
// execute request
$response = curl_exec ( $ch );

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
            <hr> Full Response<hr>
            <?= $response?>
        </td>
    </tr>
    <tr>
        <td class="half">
            <hr> Parsedl Response<hr>
            <!-- Display the transaction results as an HTML unordered list by iterating over the associative array $results -->
            <?= parseArray($results)?>
        </td>
        <td class="half">
            <iframe src="about:blank" name="tran_detail" id="tran_detail"></iframe>
        </td>
    </tr>
</table>
