var DateRangePickerInput = React.createClass({
  render: function() {
    return (
      <input id="main-date-range-picker" className="form-control pull-right" type="text" value=""/>
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
    var startDate = moment().subtract(1, 'month').format('MM/DD/YYYY');
    var endDate = moment().subtract(1, 'day').format('MM/DD/YYYY');
    $('#main-date-range-picker').daterangepicker({
      'startDate': startDate,
      'endDate': endDate
    }, function(start, end, label) {
      console.log("New date range selected: " + start.format('YYYY-MM-DD') + " to " + end.format('YYYY-MM-DD') + " (predefined range: " + label + ")");
    }).val(startDate + ' - ' + endDate);
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
