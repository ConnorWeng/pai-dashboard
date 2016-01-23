var MouseMoveWidget = React.createClass({
  render: function() {
    return (
      <div className="box box-danger">
          <WidgetHeader title="鼠标轨迹" />
          <WidgetBody>
          </WidgetBody>
          <WidgetFooter>
              <a href="#">查看详情</a>
          </WidgetFooter>
      </div>
    );
  }
});
