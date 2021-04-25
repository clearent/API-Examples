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
$uri = "https://gateway-sb.clearent.net/rest/v2/transactions";
$trans_id=0;

$headers = array (
        "Accept: " . ACCEPT,
        "Content-Type: " . CONTENT_TYPE,
        "Cache-Control: no-cache",
        "api-key: " . API_KEY
);

$trantype = $_POST ['trantype'];
$id = $_POST ['id'];

// Build the PHP associative array "$txnDetails" that contains the transaction elements
$txnDetails = array (
        "type" => $trantype,
        "id" => $id
);
// Convert the PHP associative array to JSON
$body = json_encode ( $txnDetails );
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
//curl_setopt( $ch, CURLOPT_SSL_VERIFYHOST, false );
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
            <iframe src="about:blank" name="request_detail" id="request_detail"></iframe>
        </td>
    </tr>
</table>
