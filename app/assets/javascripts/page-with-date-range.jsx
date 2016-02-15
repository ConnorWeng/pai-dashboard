var PageWithDateRange = React.createClass({
  propTypes: {
    children: React.PropTypes.element.isRequired
  },
  getInitialState: function() {
    return {
      startDate: moment(),
      endDate: moment(),
      appId: 1
    };
  },
  dateChange: function(startDate, endDate) {
    this.setState({
      startDate: startDate,
      endDate: endDate
    });
  },
  componentDidMount: function() {
    $('#app').select2().on('change', function() {
      this.setState({
        appId: $('#app').val()
      });
    }.bind(this));
  },
  render: function() {
    var child = React.cloneElement(this.props.children, {
      startDate: this.state.startDate,
      endDate: this.state.endDate,
      appId: this.state.appId
    });
    return (
      <div className="container-fluid">
          <div className="row" style={{marginBottom: '10px'}}>
              <div className="col-xs-6 col-md-4 pull-right" id="date-range-picker">
                  <DateRangePicker startDate={this.state.startDate} endDate={this.state.endDate} dateChange={this.dateChange}/>
              </div>
              <div className="col-xs-6 col-md-2 pull-right">
                  <select className="select2 form-control" id="app" name="app">
                      <option value="1">CMAS</option>
                      <option value="2">SMIS</option>
                  </select>
              </div>
          </div>
          {child}
      </div>
    );
  }
});
