var WebModuleUser = React.createClass({
  render: function() {
    return (
      <li data-toggle="tooltip" data-container="body" title={this.props.user} style={{float: 'left'}}>
          <img className="img-circle" src="/assets/images/avatar1.png" style={{maxWidth: 25}}/>
      </li>
    );
  }
});

var WebModule = React.createClass({
  render: function() {
    var index = this.props.index + 1;
    var rgba = 'rgba(' + (240/6*index) + ',' + (240-(240/6*index)) + ',0,0.5)';
    return (
      <tr style={{backgroundColor: rgba}}>
          <td>{this.props.module.moduleName}</td>
          <td>{this.props.module.moduleView}</td>
          <td>{Math.floor(this.props.module.duration / 1000).toString().toHHMMSS()}</td>
          <td>
              <ul className="no-list-style" style={{paddingLeft: 0}}>
              {
                this.props.module.machineNames.map(function(name) {
                  return <WebModuleUser user={name} key={name} />
                })
              }
              </ul>
          </td>
      </tr>
    );
  }
});

var WebModulesWidget = React.createClass({
  getInitialState: function() {
    return {
      modules: []
    };
  },
  updateState: function(props) {
    $.get('/modules/' + props.appId + '/' + props.startDate.format('YYYYMMDD') + '/' + props.endDate.format('YYYYMMDD'), function(modules) {
      this.setState({modules: modules});
    }.bind(this));
  },
  componentDidMount: function() {
    this.updateState(this.props);
  },
  componentWillReceiveProps: function(nextProps) {
    this.updateState(nextProps);
  },
  render: function() {
    var modules = [];
    for (var index = 0; index < this.state.modules.length && index < 6; index++) {
      var module = this.state.modules[index];
      modules.push(<WebModule module={module} key={module.moduleName} index={index} />);
    }
    return (
      <div className="box box-danger">
          <WidgetHeader title="网站模块" />
          <WidgetBody>
              <div className="table-responsive">
                  <table className="table no-margin">
                      <thead>
                          <tr>
                              <th>模块</th>
                              <th>访问次数</th>
                              <th>总用时</th>
                              <th>人员</th>
                          </tr>
                      </thead>
                      <tbody>
                          {modules}
                      </tbody>
                  </table>
              </div>
          </WidgetBody>
          <WidgetFooter>
              <a href="#">查看详情</a>
          </WidgetFooter>
      </div>
    );
  }
});
