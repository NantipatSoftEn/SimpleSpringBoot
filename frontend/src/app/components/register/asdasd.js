function IsMatchingCode(str) {
  var myRegExp = /^[a-zA-Z0-9]{4,10}$/;
  return myRegExp.test(str);
}

console.log(IsMatchingCode("4"));
