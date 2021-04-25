<!DOCTYPE html>
<html lang="en">
<head>
    <title>Sale Transaction</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="../css/styles.css"/>
</head>
<body>
<?php
// random number between $upper and $lower, inclusive
$random_amount = mt_rand(5, 150) . "." . mt_rand(10, 99);
?>
<div class="transaction_div">
    <div class="transaction_header">Refund Transaction</div>
    <div class="transaction_body">
        You may refund any amount up to the original transaction amount.
        <form name="transaction" action="refund.php" method="post">
            <table>
                <tr>
                    <td>Transaction Type</td>
                    <td><select name="trantype">
                        <option value="REFUND">Refund</option>
                    </select></td>
                </tr>
                <tr>
                    <td>Transaction ID to REFUND</td>
                    <td><input required type="text"
                               name="id" value=""></td>
                </tr>
                <tr>
                    <td>Amount to refund </td>
                    <td><input required type="text"
                               name="amount" value=""></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" class="btn"></td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
