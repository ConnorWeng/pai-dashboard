var MouseMoveWidget = React.createClass({
  startPlay: function() {
    var svg = d3.select('#mousemove-svg');
    var mouse = svg.append('circle')
                   .attr('r', '4')
                   .attr('fill', 'rgb(100,100,255)');
    var lines = svg.selectAll('line');
    var linesData = [];
    var inputCount = 0;
    var frameNo = 0;
    var render = function() {
      var prevPoint = data[frameNo-1];
      var nowPoint = data[frameNo];
      if (nowPoint.e === 'click' || nowPoint.e === 'mousemove') {
        mouse.attr('cx', nowPoint.x / 2.5)
             .attr('cy', nowPoint.y / 2.5)
             .style('display', function () {
               return nowPoint.e !== 'keyup';
             })
             .transition()
             .attr('r', function() {
               if (nowPoint.e === 'click') {
                 return '12';
               } else {
                 return '4';
               }
             })
             .transition()
             .attr('r', 4);
      }

      if (frameNo > 0) {
        if ((prevPoint.e === 'click' || prevPoint.e === 'mousemove') && (nowPoint.e === 'click' || nowPoint.e === 'mousemove')) {
          linesData.push({x1: prevPoint.x / 2.5, y1: prevPoint.y / 2.5, x2: nowPoint.x / 2.5, y2: nowPoint.y / 2.5});
        }
        lines.data(linesData)
             .enter()
             .append('line')
             .style('stroke', 'rgb(220,220,220)')
             .style("stroke-width", "1")
             .attr('x1', function(d, i) {return d.x1;})
             .attr('y1', function(d, i) {return d.y1;})
             .attr('x2', function(d, i) {return d.x2;})
             .attr('y2', function(d, i) {return d.y2;});
      }

      if (nowPoint.e === 'click') {
        svg.append('text')
           .attr('x', nowPoint.x / 2.5)
           .attr('y', nowPoint.y / 2.5)
           .text('[' + (++inputCount) + ']:' + nowPoint.srcElement);
      }

      if (nowPoint.e === 'keyup' && (prevPoint.e === 'click' || prevPoint.e === 'mousemove')) {
        svg.append('text')
           .attr('x', data[frameNo-1].x / 2.5)
           .attr('y', data[frameNo-1].y / 2.5)
           .text('[' + (++inputCount) + ']:' + nowPoint.srcElement + ':' + keycodes[nowPoint.keyCode]);
      }

      if (++frameNo >= data.length) {
        clearInterval(timer);
        d3.select('.play').style('display', null);
      }
    };

    var timer = setInterval(render, 300);
  },
  componentDidMount: function() {
    var width = 1680/2.5;
    var height = 1010/2.5;
    var svg = d3.select('#canvas')
      .append('svg')
      .attr('id', 'mousemove-svg')
      .attr('width', width)
      .attr('height', height);
    var play = svg.append('g')
      .attr('class', 'play');
    play.append('circle')
      .attr('r', 45)
      .attr('transform', 'translate(' + (width / 2) + ',' + height / 2 + ')');
    play.append('path')
      .attr('d', 'M-22,-30l60,30l-60,30z')
      .attr('transform', 'translate(' + (width / 2) + ',' + height / 2 + ')scale(.7)');
    play.append('rect')
      .attr('width', width)
      .attr('height', height)
      .on('mousedown', function() {
        play.classed('mousedown', true);
        d3.select(window).on('mouseup', function() {
          play.classed('mousedown', false);
        });
      })
      .on('click', function() {
        play.style('display', 'none');
        this.startPlay();
      }.bind(this));
  },
  render: function() {
    return (
      <div className="box box-danger">
          <WidgetHeader title="鼠标轨迹" />
          <WidgetBody>
              <p className="text-center">top page: <strong>TYQuery.tableQuerya</strong></p>
              <div className="canvas" id="canvas"></div>
          </WidgetBody>
          <WidgetFooter>
              <a href="#">查看详情</a>
          </WidgetFooter>
      </div>
    );
  }
});
