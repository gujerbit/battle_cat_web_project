export { removeSessionStorage };

function removeSessionStorage(name) {
  if(typeof(name) == 'string') window.sessionStorage.removeItem(name);
  else if(typeof(name) == 'object') name.forEach(res => window.sessionStorage.removeItem(res));
}