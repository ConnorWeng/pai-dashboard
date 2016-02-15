// TODO: load user config decide which widgets should be shown

var Dashboard = React.createClass({
  getInitialState: function() {
    return {
      pageViews: 0,
      sessions: 0,
      bounceRate: 0,
      uniqueVisitors: 0
    };
  },
  update: function(props) {
    $.get('/visitors/' + props.appId + '/' + props.startDate.format('YYYY-MM-DD') + '/' + props.endDate.format('YYYY-MM-DD'), function(visitors) {
      this.setState({
        pageViews: visitors.pageViews,
        sessions: visitors.sessions,
        bounceRate: visitors.bounceRate,
        uniqueVisitors: visitors.uniqueVisitors
      });
    }.bind(this));
  },
  componentDidMount: function() {
    this.update(this.props);
  },
  componentWillReceiveProps: function(nextProps) {
    this.update(nextProps);
  },
  render: function() {
    return (
      <div>
          <div className="row">
              <div className="col-lg-3 col-xs-6">
                  <div className="small-box bg-aqua">
                      <div className="inner">
                          <h3>{(this.state.pageViews / this.state.sessions).toFixed(1)}</h3>
                          <p>Pages / Session</p>
                      </div>
                      <div className="icon">
                          <i className="ion ion-pie-graph"></i>
                      </div>
                      <a href="#" className="small-box-footer">详情 <i className="fa fa-arrow-circle-right"></i></a>
                  </div>
              </div>
              <div className="col-lg-3 col-xs-6">
                  <div className="small-box bg-green">
                      <div className="inner">
                          <h3>{this.state.bounceRate}<sup style={{fontSize: '20px'}}>%</sup></h3>
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
                          <h3>{this.state.uniqueVisitors}</h3>
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
                          <h3>{this.state.pageViews}</h3>
                          <p>Page Views</p>
                      </div>
                      <div className="icon">
                          <i className="ion ion-stats-bars"></i>
                      </div>
                      <a href="#" className="small-box-footer">详情 <i className="fa fa-arrow-circle-right"></i></a>
                  </div>
              </div>
          </div>
          <div className="row">
              <div className="col-xs-12 col-md-8">
                  <WebModulesWidget appId={this.props.appId} startDate={this.props.startDate} endDate={this.props.endDate} />
                  <MouseMoveWidget appId={this.props.appId} />
              </div>
              <div className="col-xs-12 col-md-4">
                  <BrowserWidget />
              </div>
          </div>
       </div>
    );
  }
});

ReactDOM.render(
  <PageWithDateRange>
      <Dashboard />
  </PageWithDateRange>
  , document.getElementById('dashboard')
);
