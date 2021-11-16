export { clearValue, removeSessionStorage };

function clearValue(ref) {
  const key = Object.keys(ref);

  for(let i = 0; i < key.length; i++) ref[`${key[i]}`] = '';
}

function removeSessionStorage(name) {
  console.log(name);
  console.log(typeof(name));
  if(typeof(name) == 'string') window.sessionStorage.removeItem(name);
  else if(typeof(name) == 'object') name.forEach(res => window.sessionStorage.removeItem(res));
}