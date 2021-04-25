<!DOCTYPE html>
<html lang="en">
<head>
    <title>Sale Transaction</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="../css/styles.css"/>
</head>
<body>
<!--
(\d{4}[ \-]?\d{4}[ \-]?\d{4}[ \-]?(\d{1}|\d{4}))

Simple regex pattern to allow entry of 13 digit or 16 digit card numbers.
Allows all digits or groups of 4 digits separated by a dash ( - ) or a space.
You should allow users to enter card numbers how they read them; spaces and
dashes should be removed on the server.
-->
<?php
// random number between $upper and $lower, inclusive
$random_amount = mt_rand(5, 150) . "." . mt_rand(10, 99);
?>
<div class="transaction_div">
    <div class="transaction_header">Sale Transaction</div>
    <div class="transaction_body">
        <form name="transaction" action="sale.php" method="post">
            <table>
                <tr>
                    <td>Transaction Type</td>
                    <td><select name="trantype">
                        <option value="SALE">Sale</option>
                        <option value="AUTH">Authorization</option>
                    </select></td>
                </tr>
                <tr>
                    <td>Card</td>
                    <td><input required type="text"
                               name="cardnum" value="4111 1111 1111 1111"
                               pattern="(\d{4}[ \-]?\d{4}[ \-]?\d{4}[ \-]?(\d{4}|\d{1}))"
                               oninvalid="setCustomValidity('Card numbers must be 13 or 16 digits.')"
                               onchange="try{setCustomValidity('')}catch(e){}"
                               onblur="this.checkValidity();"></td>
                </tr>
                <tr>
                    <td>Amount</td>
                    <td><input required type="text"
                               name="amount" value="<?= $random_amount ?>"></td>
                </tr>
                <tr>
                    <td>ExpDate</td>
                    <td><input required type="text"
                               name="expdate"
                               value="1223"
                               pattern="((1[0-2]?|[2-9])[0-9][0-9])"
                               oninvalid="setCustomValidity('Enter month and year (myy) - do not use preceding zero for month.')"
                               onchange="try{setCustomValidity('')}catch(e){}"
                               placeholder="myy"></td>
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
