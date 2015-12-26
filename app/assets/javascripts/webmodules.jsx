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
    return (
      <tr>
          <td>{this.props.module.name}</td>
          <td>{this.props.module.clicks}</td>
          <td>{Math.floor(this.props.module.duration / 1000).toString().toHHMMSS()}</td>
          <td>
              <ul className="no-list-style" style={{paddingLeft: 0}}>
              {
                this.props.module.users.map(function(user) {
                  return <WebModuleUser user={user} key={user} />
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
  componentDidMount: function() {
    $.get(this.props.source, function(modules) {
      if (this.isMounted()) {
        this.setState({modules: modules});
      }
    }.bind(this));
  },
  render: function() {
    var modules = [];
    for (var index = 0; index < this.state.modules.length && index < 6; index++) {
      var module = this.state.modules[index];
      modules.push(<WebModule module={module} key={module.name} />);
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
