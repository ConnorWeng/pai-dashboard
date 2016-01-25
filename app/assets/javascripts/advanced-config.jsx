var AdvancedConfig = React.createClass({
  componentDidMount: function() {
    $('.advanced-config').addClass('hide');
  },
  triggerDisplay: function() {
    var $configs = $('.advanced-config')
    if ($configs.hasClass('hide')) {
      $configs.removeClass('hide');
    } else {
      $configs.addClass('hide');
    }
  },
  render: function() {
    return (
      <div>
          <div className="form-group">
              <label className="col-sm-2 control-label">
                  <input type="checkbox" onClick={this.triggerDisplay} /> 高级选项
              </label>
          </div>
          {this.props.children}
      </div>
    );
  }
});
