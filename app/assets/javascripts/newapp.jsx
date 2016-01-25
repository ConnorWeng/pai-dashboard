var Newapp = React.createClass({
  render: function() {
    return (
      <div className="container-fluid">
          <div className="row">
              <div className="col-md-12">
                  <div className="nav-tabs-custom">
                      <ul className="nav nav-tabs">
                          <li className="active"><a href="#website" data-toggle="tab" aria-expanded="true">网站</a></li>
                          <li className=""><a href="#app" data-toggle="tab" aria-expanded="false">移动APP</a></li>
                      </ul>
                      <div className="tab-content">
                          <div className="tab-pane active" id="website">
                              <form className="form-horizontal">
                                  <div className="form-group">
                                      <label data-for="appName" className="col-sm-2 control-label">应用名称</label>

                                      <div className="col-sm-10">
                                          <input type="text" className="form-control" id="appName" placeholder="BMDP" />
                                      </div>
                                  </div>
                                  <div className="form-group">
                                      <label data-for="appUrl" className="col-sm-2 control-label">应用地址</label>

                                      <div className="col-sm-10">
                                          <input type="text" className="form-control" id="appUrl" placeholder="http://bmdp.icbc" />
                                      </div>
                                  </div>
                                  <div className="form-group">
                                      <label data-for="appDepartment" className="col-sm-2 control-label">所属部门</label>

                                      <div className="col-sm-10">
                                          <input type="text" className="form-control" id="appDepartment" placeholder="上海研发支持部" />
                                      </div>
                                  </div>
                                  <div className="form-group">
                                      <label data-for="appGroup" className="col-sm-2 control-label">可见范围</label>

                                      <div className="col-sm-10">
                                          <input type="text" className="form-control" id="appGroup" placeholder="技术支持组" />
                                      </div>
                                  </div>
                                  <AdvancedConfig>
                                      <div className="form-group advanced-config">
                                          <label data-for="events" className="col-sm-2 control-label">追踪事件</label>

                                          <div className="col-sm-10">
                                              <input type="text" className="form-control" id="events" placeholder="鼠标移动、鼠标点击、键盘输入、性能指标" />
                                          </div>
                                      </div>
                                      <div className="form-group advanced-config">
                                          <label data-for="conditions" className="col-sm-2 control-label">追踪条件</label>

                                          <div className="col-sm-10">
                                              <input type="text" className="form-control" id="conditions" placeholder="追踪地区用户、追踪用户数量占总量百分比" />
                                          </div>
                                      </div>
                                  </AdvancedConfig>
                                  <div className="form-group">
                                      <div className="col-sm-offset-2 col-sm-10">
                                          <div className="checkbox">
                                              <label>
                                                  <input type="checkbox" /> 我同意 <a href="#">埋点协议</a>
                                              </label>
                                          </div>
                                      </div>
                                  </div>
                                  <div className="form-group">
                                      <div className="col-sm-offset-2 col-sm-10">
                                          <button type="submit" className="btn btn-danger">开始追踪</button>
                                      </div>
                                  </div>
                              </form>
                          </div>

                          <div className="tab-pane" id="app">
                              <form className="form-horizontal">
                                  <div className="form-group">
                                      <label data-for="inputName" className="col-sm-2 control-label">Name</label>

                                      <div className="col-sm-10">
                                          <input type="email" className="form-control" id="inputName" placeholder="Name" />
                                      </div>
                                  </div>
                                  <div className="form-group">
                                      <label data-for="inputEmail" className="col-sm-2 control-label">Email</label>

                                      <div className="col-sm-10">
                                          <input type="email" className="form-control" id="inputEmail" placeholder="Email" />
                                      </div>
                                  </div>
                                  <div className="form-group">
                                      <label data-for="inputName" className="col-sm-2 control-label">Name</label>

                                      <div className="col-sm-10">
                                          <input type="text" className="form-control" id="inputName" placeholder="Name" />
                                      </div>
                                  </div>
                                  <div className="form-group">
                                      <label data-for="inputExperience" className="col-sm-2 control-label">Experience</label>

                                      <div className="col-sm-10">
                                          <textarea className="form-control" id="inputExperience" placeholder="Experience"></textarea>
                                      </div>
                                  </div>
                                  <div className="form-group">
                                      <label data-for="inputSkills" className="col-sm-2 control-label">Skills</label>

                                      <div className="col-sm-10">
                                          <input type="text" className="form-control" id="inputSkills" placeholder="Skills" />
                                      </div>
                                  </div>
                                  <div className="form-group">
                                      <div className="col-sm-offset-2 col-sm-10">
                                          <div className="checkbox">
                                              <label>
                                                  <input type="checkbox" /> I agree to the<a href="#">terms and conditions</a>
                                              </label>
                                          </div>
                                      </div>
                                  </div>
                                  <div className="form-group">
                                      <div className="col-sm-offset-2 col-sm-10">
                                          <button type="submit" className="btn btn-danger">Submit</button>
                                      </div>
                                  </div>
                              </form>
                          </div>
                      </div>
                  </div>
              </div>
          </div>
      </div>
    );
  }
});

ReactDOM.render(
  <Newapp />,
  document.getElementById('newapp')
);
