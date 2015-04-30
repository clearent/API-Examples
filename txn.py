import os
import cgi
from mod_python import apache, Session, util
import ClearentGateway

def index(req):

    # sandbox url
    url = 'https://gateway-sb.clearent.net/rest/v2/transactions'

    # get the api key from the apikey file
    __location__ = os.path.realpath(os.path.join(os.getcwd(), os.path.dirname(__file__)))
    f = open(os.path.join(__location__, "apikey.txt"),"r");
    apikey = f.readline()
    f.close()

    cg = ClearentGateway.ClearentGateway(url, apikey)

    # set up a sale
    transaction = {
        "type": "SALE",
        "amount": "25.00",
        "card": "4111111111111111",
        "exp-date": "1219"
    }
    resp = cg.PostTransaction(transaction)
    print(resp)

    # set up a authorization
    transaction = {
        "type": "AUTH",
        "amount": "25.00",
        "card": "4111111111111111",
        "exp-date": "1219"
    }
    resp = cg.PostTransaction(transaction)
    print(resp)

    # set up a capture
    transaction = {
        "type": "CAPTURE",
        "amount": "25.00",
        "id": "12345"
    }
    resp = cg.PostTransaction(transaction)
    print(resp)

    # set up a forced sale
    transaction = {
        "type": "FORCED SALE",
        "amount": "25.00",
        "card": "4111111111111111",
        "exp-date": "1219",
        "order-id": "123456",
        "authorization-code": "APPC10"
    }
    resp = cg.PostTransaction(transaction)
    print(resp)

    # set up a refund
    transaction = {
        "type": "REFUND",
        "amount": "25.00",
        "card": "4111111111111111",
        "id": "12345"
    }
    resp = cg.PostTransaction(transaction)
    print(resp)

    # set up a void
    transaction = {
        "type": "SALE",
        "id": "12345"
    }
    resp = cg.PostTransaction(transaction)
    print(resp)
