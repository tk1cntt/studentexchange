import React from 'react';
import { connect } from 'react-redux';
import { NavLink as Link } from 'react-router-dom';

import { IRootState } from 'app/shared/reducers';
import { showDrawer } from 'app/shared/reducers/setting';
import { getOwnerBalance as getUserBalance } from 'app/entities/user-balance/user-balance.reducer';
import { getByCurrency } from 'app/entities/currency-rate/currency-rate.reducer';
import { formatCurency } from 'app/shared/util/utils';

export interface IHeaderProps extends StateProps, DispatchProps {}

export interface IHeaderState {
  showModal: boolean;
  width: number;
  height: number;
}

export class Header extends React.Component<IHeaderProps, IHeaderState> {
  state: IHeaderState = {
    showModal: true,
    width: 0,
    height: 0
  };

  componentDidMount() {
    this.props.getUserBalance('');
    this.props.getByCurrency('CNY');
    this.updateDimensions();
    window.addEventListener('resize', this.updateDimensions);
  }

  componentWillUnmount() {
    window.removeEventListener('resize', this.updateDimensions);
  }

  updateDimensions = () => {
    const w = window;
    const d = document;
    const documentElement = d.documentElement;
    const body = d.getElementsByTagName('body')[0];
    const width = w.innerWidth || documentElement.clientWidth || body.clientWidth;
    const height = w.innerHeight || documentElement.clientHeight || body.clientHeight;
    this.setState({ width, height });
  };

  openMiniNavbar = () => () => {
    /*
    if (this.state.showModal) {
      document.body.classList.add('mini-navbar');
    } else {
      document.body.classList.remove('mini-navbar');
    }
    this.setState({ showModal: !this.state.showModal });
    */
    this.props.showDrawer(true);
  };

  navbarMinimalizeButton = () => {
    if (this.state.width <= 768) {
      return (
        <div className="navbar-minimalize minimalize-styl-2 btn btn-primary" onClick={this.openMiniNavbar()}>
          <i className="fa fa-bars" />{' '}
        </div>
      );
    }
  };

  render() {
    return (
      <>
        <div className="row border-bottom">
          <nav className="navbar navbar-static-top" role="navigation" style={{ marginBottom: 0 }}>
            <div className="navbar-header">{this.navbarMinimalizeButton()}</div>
            <ul className="nav navbar-top-links navbar-right">
              <li>
                Tỷ giá: <b className="text-danger">{formatCurency(this.props.currencyRateEntity.rate)}đ</b>
              </li>
              <li className="dropdown">
                <a className="dropdown-toggle count-info" data-toggle="dropdown" href="#">
                  <i className="fa fa-envelope" /> <span className="label label-warning">16</span>
                </a>
                <ul className="dropdown-menu dropdown-messages">
                  <li>
                    <div className="dropdown-messages-box">
                      <a href="profile.html" className="pull-left">
                        <img alt="image" className="img-circle" src="content/img/a7.jpg" />
                      </a>
                      <div className="media-body">
                        <small className="pull-right">46h ago</small>
                        <strong>Mike Loreipsum</strong> started following <strong>Monica Smith</strong>. <br />
                        <small className="text-muted">3 days ago at 7:58 pm - 10.06.2014</small>
                      </div>
                    </div>
                  </li>
                  <li className="divider" />
                  <li>
                    <div className="dropdown-messages-box">
                      <a href="profile.html" className="pull-left">
                        <img alt="image" className="img-circle" src="content/img/a4.jpg" />
                      </a>
                      <div className="media-body ">
                        <small className="pull-right text-navy">5h ago</small>
                        <strong>Chris Johnatan Overtunk</strong> started following <strong>Monica Smith</strong>. <br />
                        <small className="text-muted">Yesterday 1:21 pm - 11.06.2014</small>
                      </div>
                    </div>
                  </li>
                  <li className="divider" />
                  <li>
                    <div className="dropdown-messages-box">
                      <a href="profile.html" className="pull-left">
                        <img alt="image" className="img-circle" src="content/img/profile.jpg" />
                      </a>
                      <div className="media-body ">
                        <small className="pull-right">23h ago</small>
                        <strong>Monica Smith</strong> love <strong>Kim Smith</strong>. <br />
                        <small className="text-muted">2 days ago at 2:30 am - 11.06.2014</small>
                      </div>
                    </div>
                  </li>
                  <li className="divider" />
                  <li>
                    <div className="text-center link-block">
                      <a href="mailbox.html">
                        <i className="fa fa-envelope" /> <strong>Read All Messages</strong>
                      </a>
                    </div>
                  </li>
                </ul>
              </li>
              <li className="dropdown">
                <a className="dropdown-toggle count-info" data-toggle="dropdown" href="#">
                  <i className="fa fa-bell" /> <span className="label label-primary">8</span>
                </a>
                <ul className="dropdown-menu dropdown-alerts">
                  <li>
                    <a href="mailbox.html">
                      <div>
                        <i className="fa fa-envelope fa-fw" /> You have 16 messages
                        <span className="pull-right text-muted small">4 minutes ago</span>
                      </div>
                    </a>
                  </li>
                  <li className="divider" />
                  <li>
                    <a href="profile.html">
                      <div>
                        <i className="fa fa-twitter fa-fw" /> 3 New Followers
                        <span className="pull-right text-muted small">12 minutes ago</span>
                      </div>
                    </a>
                  </li>
                  <li className="divider" />
                  <li>
                    <a href="grid_options.html">
                      <div>
                        <i className="fa fa-upload fa-fw" /> Server Rebooted
                        <span className="pull-right text-muted small">4 minutes ago</span>
                      </div>
                    </a>
                  </li>
                  <li className="divider" />
                  <li>
                    <div className="text-center link-block">
                      <a href="notifications.html">
                        <strong>See All Alerts</strong>
                        <i className="fa fa-angle-right" />
                      </a>
                    </div>
                  </li>
                </ul>
              </li>
              <li>
                <Link to={'/logout'}>
                  <i className="fa fa-sign-out" /> Log out
                </Link>
              </li>
              <li>
                <a className="right-sidebar-toggle">
                  <i className="fa fa-tasks" />
                </a>
              </li>
            </ul>
          </nav>
        </div>
      </>
    );
  }
}

const mapStateToProps = ({ currencyRate, setting }) => ({
  currencyRateEntity: currencyRate.entity,
  setting
});

const mapDispatchToProps = {
  getByCurrency,
  getUserBalance,
  showDrawer
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Header);
