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
          <td>{this.props.module.name}<span style={{fontSize: '12px'}}>(访问: {this.props.module.clicks})</span></td>
          <td>{this.props.module.duration}</td>
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
      modules: [
        {name: '模块1', clicks: '17', duration: '11:12:03', users: ['user-a', 'user-b', 'user-c', 'user-d']},
        {name: '模块2', clicks: '2', duration: '01:13:43', users: ['user-c']}
      ]
    };
  },
  componentWillMount: function() {
    $.ajax()
  },
  render: function() {
    return (
      <div className="box box-danger">
          <WidgetHeader title="网站模块" />
          <WidgetBody>
              <div className="table-responsive">
                  <table className="table no-margin">
                      <thead>
                          <tr>
                              <th>模块</th>
                              <th>总用时</th>
                              <th>人员</th>
                          </tr>
                      </thead>
                      <tbody>
                          {
                            this.state.modules.map(function(module) {
                              return <WebModule module={module} key={module.name} />;
                            })
                          }
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
