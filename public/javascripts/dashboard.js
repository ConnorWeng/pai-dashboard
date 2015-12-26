// TODO: load user config decide which widgets should be shown

ReactDOM.render(
  React.createElement(WebModulesWidget, {source: '/webmodules/modules'}),
  document.getElementById('web-modules-widget')
);
