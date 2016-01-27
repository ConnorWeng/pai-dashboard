var BrowserWidget = React.createClass({
  componentDidMount: function() {
    var browserPie = echarts.init($('.browser-pie')[0]);
    var option = {
      tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
      },
      legend: {
        orient: 'vertical',
        x: 'right',
        data:['IE6','IE8','IE11','Chrome','FireFox']
      },
      series: [
        {
          name:'访问来源',
          type:'pie',
          radius: ['50%', '70%'],
          avoidLabelOverlap: false,
          label: {
            normal: {
              show: false,
              position: 'center'
            },
            emphasis: {
              show: true,
              textStyle: {
                fontSize: '30',
                fontWeight: 'bold'
              }
            }
          },
          labelLine: {
            normal: {
              show: false
            }
          },
          data:[
            {value:0, name:'IE6'},
            {value:3, name:'IE8'},
            {value:3, name:'IE11'},
            {value:1, name:'Chrome'},
            {value:0, name:'FireFox'}
          ]
        }
      ]
    };
    browserPie.setOption(option);
  },
  render: function() {
    return (
      <div className="box box-default">
          <div className="box-header with-border">
              <h3 className="box-title">浏览器</h3>
              <div className="box-tools pull-right">
                  <button type="button" className="btn btn-box-tool" data-widget="collapse"><i className="fa fa-minus"></i>
                  </button>
                  <button type="button" className="btn btn-box-tool" data-widget="remove"><i className="fa fa-times"></i></button>
              </div>
          </div>
          <div className="box-body">
              <div className="row browser-pie" style={{height: 160}}></div>
          </div>
          <div className="box-footer no-padding">
              <ul className="nav nav-pills nav-stacked">
                  <li><a href="#">上海
                      <span className="pull-right text-red"><i className="fa fa-angle-down"></i> 100%</span></a></li>
                  <li><a href="#">珠海 <span className="pull-right text-green"><i className="fa fa-angle-up"></i> 0%</span></a>
                  </li>
                  <li><a href="#">北京
                      <span className="pull-right text-yellow"><i className="fa fa-angle-left"></i> 0%</span></a></li>
              </ul>
              <div className="text-center" style={{padding: 10}}>
                  <a href="#">查看详情</a>
              </div>
          </div>
      </div>
    );
  }
});
