<?php

$id = $_GET ['id'];
// Set the API URI
$uri = "https://gateway-sb.clearent.net/rest/v2/transactions?id=" . $id;
$contenttype = "text/html";
$apikey = "12fa1a5617464354a72b3c9eb92d4f3b";

$headers = array (
        "Accept: " . $contenttype,
        "Content-Type: " . $contenttype,
        "Cache-Control: no-cache",
        "api-key: " . $apikey
);

$ch = curl_init ();

// set the url
curl_setopt ( $ch, CURLOPT_URL, $uri );
// set the headers
curl_setopt ( $ch, CURLOPT_HTTPHEADER, $headers );
// return full response if curl_exec is successful
curl_setopt ( $ch, CURLOPT_RETURNTRANSFER, 1 );
// execute request
$response = curl_exec ( $ch );

?>
<?= $uri?>

<hr>
<?= $response?>
