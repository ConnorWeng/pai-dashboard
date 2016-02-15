var VisitorOverview = React.createClass({
  update: function(props) {
    var dates = [props.startDate.format('YYYY-MM-DD')];
    var m = moment(props.startDate);
    while (!m.isSame(props.endDate, 'day')) {
      m.add(1, 'days');
      dates.push(m.format('YYYY-MM-DD'));
    }
    $.get('/visitor/overview/daily/' + props.appId + '/' + props.startDate.format('YYYY-MM-DD') + '~' + props.endDate.format('YYYY-MM-DD'), function(data) {
      var chart = echarts.init($('.overview-chart')[0]);
      var option = {
        backgroundColor: '#fff',
        tooltip : {
          trigger: 'axis'
        },
        legend: {
          data:['pageview', 'unique visitor', 'pages/session']
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        toolbox: {
          feature: {
            saveAsImage: {}
          }
        },
        xAxis : [
          {
            type : 'category',
            boundaryGap : false,
            data : dates
          }
        ],
        yAxis : [
          {
            type : 'value'
          }
        ],
        series : [
          {
            name: 'pageview',
            type: 'line',
            data: data['pageview']
          },
          {
            name: 'unique visitor',
            type: 'line',
            data: data['unique_visitor']
          },
          {
            name: 'pages/session',
            type: 'line',
            data: data['pageview_per_session']
          }
        ]
      };
      chart.setOption(option);
    });
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
              <div className="col-xs-12 overview-chart" style={{height: 200}}>
              </div>
          </div>
      </div>
    );
  }
});

ReactDOM.render(
  <PageWithDateRange>
      <VisitorOverview />
  </PageWithDateRange>
  , document.getElementById('visitor-overview')
)
