import requests # http://python-requests.org
import json

class ClearentGateway:
    def __init__(self, baseUrl, apiKey):
        self.baseUrl = baseUrl
        self.headers = {'Content-Type': 'application/json',
                        'Accept':'application/json',
                        'Cache-Control':'no-cache',
                        'api-key' : apiKey
                        }

    def PostTransaction(self, transaction):
        """Post the Transaction (as json) to the Gateway and 
           return a Dictionary if successful, full HTTP response message if not. """
        print('POST: \n{0}'.format(json.dumps(transaction, indent=4, sort_keys=False)))
        resp = requests.post(self.baseUrl, data=json.dumps(transaction), headers=self.headers)
        print('Status: {0} ({1}) '.format(resp.status_code,resp.reason))
        if resp.status_code in range(200,300) or resp.status_code in range(400,500):
            # 2xx and 4xx responses have detail content that should be parsed.
            respDict = resp.json()
            print('Response: \n{0}'.format(json.dumps(respDict, indent=4, sort_keys=False)))
            return respDict
        return resp

    def GetTransaction(self, id):
        """ Get Transaction as object from the Gateway by ID. """
        url = self.baseUrl + '?id={0}'.format(id)
        print("GET: \n{0}".format(url))
        resp = requests.get(url, headers=self.headers)
        print('Status: {0} ({1}) '.format(resp.status_code,resp.reason))
        if resp.status_code == 200:
            respList = resp.json()
            print('Response: \n{0}'.format(json.dumps(respList[0], indent=4, sort_keys=False)))
            return respList[0]
        return resp


