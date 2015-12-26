var WidgetFooter = React.createClass({
  render: function() {
    return (
      <div className="box-footer text-center">
          {this.props.children}
      </div>
    );
  }
});

var WidgetBody = React.createClass({
  render: function() {
    return (
      <div className="box-body">
          {this.props.children}
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
