var DateRangePickerInput = React.createClass({
  render: function() {
    return (
      <input id="main-date-range-picker" className="form-control pull-right" type="text" value="" />
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
  updateDate: function(props) {
    var startDate = props.startDate.format('MM/DD/YYYY');
    var endDate = props.endDate.format('MM/DD/YYYY');
    $('#main-date-range-picker').val(startDate + ' - ' + endDate);
  },
  componentDidMount: function() {
    $('#main-date-range-picker').daterangepicker({
      'startDate': this.props.startDate,
      'endDate': this.props.endDate
    }, function(start, end, label) {
      this.props.dateChange(start, end);
    }.bind(this));
    this.updateDate(this.props);
  },
  componentDidUpdate: function(prevProps) {
    this.updateDate(this.props);
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
