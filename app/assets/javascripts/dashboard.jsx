// TODO: load user config decide which widgets should be shown

var Dashboard = React.createClass({
  getInitialState: function() {
    return {
      startDate: moment().subtract(1, 'month'),
      endDate: moment().subtract(1, 'day')
    };
  },
  dateChange: function(startDate, endDate) {
    this.setState({
      startDate: startDate,
      endDate: endDate
    });
  },
  render: function() {
    return (
      <div className="container-fluid">
          <div className="row" style={{marginBottom: '10px'}}>
              <div className="col-xs-4 pull-right" id="date-range-picker">
                  <DateRangePicker trigger={this.trigger} startDate={this.state.startDate} endDate={this.state.endDate} dateChange={this.dateChange}/>
              </div>
          </div>
          <div className="row">
              <div className="col-lg-3 col-xs-6">
                  <div className="small-box bg-aqua">
                      <div className="inner">
                          <h3>150</h3>
                          <p>Pages / Session</p>
                      </div>
                      <div className="icon">
                          <i className="ion ion-bag"></i>
                      </div>
                      <a href="#" className="small-box-footer">详情 <i className="fa fa-arrow-circle-right"></i></a>
                  </div>
              </div>
              <div className="col-lg-3 col-xs-6">
                  <div className="small-box bg-green">
                      <div className="inner">
                          <h3>53<sup style={{fontSize: '20px'}}>%</sup></h3>
                          <p>Bounce Rate</p>
                      </div>
                      <div className="icon">
                          <i className="ion ion-stats-bars"></i>
                      </div>
                      <a href="#" className="small-box-footer">详情 <i className="fa fa-arrow-circle-right"></i></a>
                  </div>
              </div>
              <div className="col-lg-3 col-xs-6">
                  <div className="small-box bg-yellow">
                      <div className="inner">
                          <h3>44</h3>
                          <p>Unique Visitors</p>
                      </div>
                      <div className="icon">
                          <i className="ion ion-person-add"></i>
                      </div>
                      <a href="#" className="small-box-footer">详情 <i className="fa fa-arrow-circle-right"></i></a>
                  </div>
              </div>
              <div className="col-lg-3 col-xs-6">
                  <div className="small-box bg-red">
                      <div className="inner">
                          <h3>65</h3>
                          <p>Page Views</p>
                      </div>
                      <div className="icon">
                          <i className="ion ion-pie-graph"></i>
                      </div>
                      <a href="#" className="small-box-footer">详情 <i className="fa fa-arrow-circle-right"></i></a>
                  </div>
              </div>
          </div>
          <div className="row">
              <div className="col-xs-12 col-md-8">
                  <div className="row">
                      <div className="col-xs-12" id="web-modules-widget">
                          <WebModulesWidget source="/webmodules/modules" startDate={this.state.startDate} endDate={this.state.endDate} />
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
