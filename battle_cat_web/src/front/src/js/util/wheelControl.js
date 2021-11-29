export { wheelLevelChange, wheelInstinctLevelChange, scrollPrevent, overflowScrollApply };

function wheelLevelChange(index, wathcing, value) {
  wathcing.scroll = true;

  window.onmousewheel = e => {
    if(wathcing.scroll) value.level[index] += Math.floor(e.wheelDelta / 120);
  }
}

function wheelInstinctLevelChange(event, index, watching, value) {
  watching.scroll = true;
  let input = event.currentTarget;
  let content = event.currentTarget.closest('.content');
  content.style.pointerEvents = 'none';
  input.style.pointerEvents = 'auto';

  window.onmousewheel = e => {
    if(watching.scroll) {
      value.instinctLevel[index] += Math.floor(e.wheelDelta / 120);
    }
  };
}

function scrollPrevent(watching) {
  watching.scroll = false;
}

function overflowScrollApply(event) {
  let content = event.currentTarget.closest('.content');
  content.style.pointerEvents = 'auto';
}