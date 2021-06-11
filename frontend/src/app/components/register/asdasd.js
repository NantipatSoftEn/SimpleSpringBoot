function IsMatchingCode(str) {
  var myRegExp = /^[a-zA-Z0-9]{8,25}$/;
  return myRegExp.test(str);
}

function IsMatchingCode2(str) {
  var myRegExp = /^[a-zA-Z0-9]$/;
  return myRegExp.test(str);
}

console.log(IsMatchingCode2(""));
