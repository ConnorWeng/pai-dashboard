// TODO: load user config decide which widgets should be shown

var Dashboard = React.createClass({
  render: function() {
    return (
      <div className="container-fluid">
          <div className="row" style={{marginBottom: '10px'}}>
              <div className="col-xs-4 pull-right" id="date-range-picker">
                  <DateRangePicker />
              </div>
          </div>
          <div className="row">
              <div className="col-xs-12 col-md-8">
                  <div className="row">
                      <div className="col-xs-12" id="web-modules-widget">
                          <WebModulesWidget source="/webmodules/modules" />
                      </div>
                  </div>
              </div>
              <div className="col-xs-12 col-md-4">
                  <div className="box box-danger">
                      <div className="box-header with-border">
                          <h3 className="box-title">鼠标轨迹</h3>
                          <div className="box-tools pull-right">
                              <button className="btn btn-box-tool" type="button" data-widget="collapse">
                                  <i className="fa fa-minus"></i>
                              </button>
                              <button className="btn btn-box-tool" type="button" data-widget="remove">
                                  <i className="fa fa-times"></i>
                              </button>
                          </div>
                      </div>
                      <div className="box-body"></div>
                      <div className="box-footer no-padding"></div>
                  </div>
              </div>
          </div>
      </div>
    );
  }
});

ReactDOM.render(
  <Dashboard />,
  document.getElementById('dashboard')
);
