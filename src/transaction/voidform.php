<!DOCTYPE html>
<html lang="en">
<head>
    <title>Sale Transaction</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="../css/styles.css"/>
</head>
<body>
<div class="transaction_div">
    <div class="transaction_header">Void Transaction</div>
    <div class="transaction_body">
        You may void a transaction until the batch has be been closed. After that, a transaction can be refunded but
        can no longer be voided. When possible it is preferable to void a transaction as this will save the merchant on interchange.
        <form name="transaction" action="void.php" method="post">
            <table>
                <tr>
                    <td>Transaction Type</td>
                    <td><select name="trantype">
                        <option value="VOID">Void</option>
                    </select></td>
                </tr>
                <tr>
                    <td>Transaction ID to VOID</td>
                    <td><input required type="text"
                               name="id" value=""></td>
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
