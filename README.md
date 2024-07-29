The application was tested on Mockoon server with following Faker data format:
```json
[
  {{#repeat 50}}
  {
    "id": "{{faker 'datatype.uuid'}}",
    "name": "{{faker 'commerce.productName'}} Inc.",
    "description": "{{faker 'company.catchPhrase'}}",
    "turnover": {{faker 'finance.amount' min=10000 max=10000000 precision=0.01}},
    "logoUrl": "https://www.gravatar.com/avatar/{{faker 'datatype.uuid'}}?d=identicon&s=150",
    "type": "{{oneOf (array 'ENTERTAINMENT' 'TRADE' 'IT')}}",
    "isVatPayer": {{faker 'datatype.boolean'}}
  }
  {{/repeat}}
]
```
