var WidgetFooter = React.createClass({
  render: function() {
    return (
      <div className="box-footer text-center">
        <a className="uppercase" href="#">查看详情</a>
      </div>
    );
  }
});

var WidgetBody = React.createClass({
  render: function() {
    return (
      <div className="box-body">
        <div className="table-responsive">
          <table className="table no-margin">
            <thead>
              <tr>
                <th>模块</th>
                <th>总用时</th>
                <th>人员</th>
                <th>事件</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>test text</td>
                <td>test text</td>
                <td>test text</td>
                <td>test text</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    );
  }
});

var WidgetHeader = React.createClass({
  render: function() {
    return (
      <div className="box-header with-border">
        <h3 className="box-title">{this.props.title}</h3>
        <div className="box-tools pull-right">
          <button className="btn btn-box-tool" type="button" data-widget="collapse">
            <i className="fa fa-minus"></i>
          </button>
          <button className="btn btn-box-tool" type="button" data-widget="remove">
            <i className="fa fa-times"></i>
          </button>
        </div>
      </div>
    );
  }
});

var Widget = React.createClass({
  render: function() {
    return (
      <div className="box box-danger">
        <WidgetHeader title={this.props.title} />
        <WidgetBody />
        <WidgetFooter />
      </div>
    );
  }
});