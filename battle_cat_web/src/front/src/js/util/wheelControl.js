export { wheelLevelChange, wheelInstinctLevelChange, scrollPrevent, overflowScrollApply };

function wheelLevelChange(index, watching, value) {
  watching.scroll = true;

  window.onmousewheel = e => {
    if(watching.scroll) value.level[index] += e.wheelDelta / 180;
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
      value.instinctLevel[index] += e.wheelDelta / 180;
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