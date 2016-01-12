var DateRangePickerInput = React.createClass({
  render: function() {
    return (
      <input id="reservation" className="form-control pull-right" type="text" value=""/>
    );
  }
});

var DateRangePickerButton = React.createClass({
  render: function() {
    return (
      <div className="input-group-addon">
          <i className="fa fa-calendar"></i>
      </div>
    );
  }
});

var DateRangePicker = React.createClass({
  componentDidMount: function() {
    $('#reservation').daterangepicker();
  },
  render: function() {
    return (
      <div className="input-group">
          <DateRangePickerButton />
          <DateRangePickerInput />
      </div>
    );
  }
});
