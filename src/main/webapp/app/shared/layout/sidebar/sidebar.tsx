import React from 'react';
import { connect } from 'react-redux';
import { NavLink as Link } from 'react-router-dom';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Translate, translate } from 'react-jhipster';
import { hasAnyAuthority } from 'app/shared/auth/private-route';
import { AUTHORITIES } from 'app/config/constants';

export interface ISidebarProps {
  account: any;
  isAuthenticated: boolean;
  isManager: boolean;
  isAdmin: boolean;
  isStaff: boolean;
  activeMenu: string;
  activeSubMenu: string;
}

export class Sidebar extends React.Component<ISidebarProps> {
  userMenu() {
    const { activeMenu, activeSubMenu } = this.props;

    return (
      <li className={`${activeMenu === 'user-management' ? 'active' : ''}`}>
        <Link to={'/account/settings'}>
          <i className="fa fa-user" /> <span className="nav-label">Cá nhân</span> <span className="fa arrow" />
        </Link>
        <ul className={`${activeMenu === 'user-management' ? 'nav nav-second-level collapse in' : 'nav nav-second-level collapse'}`}>
          <li className={`${activeSubMenu === 'setting' ? 'active' : ''}`}>
            <Link to={'/account/settings'}>
              <FontAwesomeIcon icon="wrench" fixedWidth /> Thông tin cá nhân
            </Link>
          </li>
          <li className={`${activeSubMenu === 'change-password' ? 'active' : ''}`}>
            <Link to={'/account/password'}>
              <FontAwesomeIcon icon="clock" fixedWidth /> Thay đổi mật khẩu
            </Link>
          </li>
        </ul>
      </li>
    );
  }

  managerMenu() {
    const { activeMenu, activeSubMenu, isManager } = this.props;
    if (!isManager) return '';
    return (
      <li className={`${activeMenu === 'manager-management' ? 'active' : ''}`}>
        <Link to={'/management/banktransfer'}>
          <i className="fa fa-gift" /> <span className="nav-label">Payment</span> <span className="fa arrow" />
        </Link>
        <ul className={`${activeMenu === 'manager-management' ? 'nav nav-second-level collapse in' : 'nav nav-second-level collapse'}`}>
          <li className={`${activeSubMenu === 'banktransfer' ? 'active' : ''}`}>
            <Link to={'/management/banktransfer'}>
              <i className="fa fa-sign-in" /> Nạp tiền
            </Link>
          </li>
          <li className={`${activeSubMenu === 'change-password' ? 'active' : ''}`}>
            <Link to={'/payment/history'}>
              <FontAwesomeIcon icon="clock" /> Lịch sử thanh toán
            </Link>
          </li>
        </ul>
      </li>
    );
  }

  adminMenu() {
    const { activeMenu, activeSubMenu, isAdmin } = this.props;
    if (!isAdmin) return '';
    return (
      <li className={`${activeMenu === 'administration' ? 'active' : ''}`}>
        <Link to={'/admin/user-management'}>
          <i className="fa fa-cog" /> <span className="nav-label">Administration</span> <span className="fa arrow" />
        </Link>
        <ul className={`${activeMenu === 'administration' ? 'nav nav-second-level collapse in' : 'nav nav-second-level collapse'}`}>
          <li className={`${activeSubMenu === 'user-management' ? 'active' : ''}`}>
            <Link to={'/admin/user-management'}>
              <FontAwesomeIcon icon="user" fixedWidth />
              Users
            </Link>
          </li>
          <li className={`${activeSubMenu === 'metrics' ? 'active' : ''}`}>
            <Link to={'/admin/metrics'}>
              <FontAwesomeIcon icon="tachometer-alt" fixedWidth /> Metrics
            </Link>
          </li>
          <li className={`${activeSubMenu === 'health' ? 'active' : ''}`}>
            <Link to={'/admin/health'}>
              <FontAwesomeIcon icon="heart" fixedWidth /> Health
            </Link>
          </li>
          <li className={`${activeSubMenu === 'configuration' ? 'active' : ''}`}>
            <Link to={'/admin/configuration'}>
              <FontAwesomeIcon icon="list" fixedWidth /> Configuration
            </Link>
          </li>
          <li className={`${activeSubMenu === 'audits' ? 'active' : ''}`}>
            <Link to={'/admin/audits'}>
              <FontAwesomeIcon icon="bell" fixedWidth /> Audits
            </Link>
          </li>
          <li className={`${activeSubMenu === 'logs' ? 'active' : ''}`}>
            <Link to={'/admin/logs'}>
              <FontAwesomeIcon icon="tasks" fixedWidth /> Logs
            </Link>
          </li>
        </ul>
      </li>
    );
  }

  render() {
    const { isAuthenticated, activeMenu, activeSubMenu } = this.props;
    // if (isAuthenticated !== true) return (<div />);
    return (
      <nav className="navbar-default navbar-static-side" role="navigation">
        <div className="sidebar-collapse">
          <ul className="nav metismenu" id="side-menu">
            <li className="nav-header">
              <div className="dropdown profile-element">
                {' '}
                <span>
                  <img alt="image" className="img-circle" src="content/img/profile_small.jpg" />
                </span>
                <a data-toggle="dropdown" className="dropdown-toggle" href="#">
                  <span className="clear">
                    {' '}
                    <span className="block m-t-xs">
                      {' '}
                      <strong className="font-bold">{this.props.account.login}</strong>
                    </span>{' '}
                    <span className="text-muted text-xs block">
                      Manager <b className="caret" />
                    </span>{' '}
                  </span>{' '}
                </a>
                <ul className="dropdown-menu animated fadeInRight m-t-xs">
                  <li>
                    <a href="profile.html">Profile</a>
                  </li>
                  <li>
                    <a href="contacts.html">Contacts</a>
                  </li>
                  <li>
                    <a href="mailbox.html">Mailbox</a>
                  </li>
                  <li className="divider" />
                  <li>
                    <Link to={'/logout'}>Logout</Link>
                  </li>
                </ul>
              </div>
              <div className="logo-element">IN+</div>
            </li>
            <li className={`${activeMenu === 'dashboard' ? 'active' : ''}`}>
              <Link to={'/'}>
                <i className="fa fa-th-large" /> <span className="nav-label">Thông tin chung</span>
              </Link>
            </li>
            <li className={`${activeMenu === 'shopping-cart' ? 'active' : ''}`}>
              <Link to={'/shopping-cart'}>
                <i className="fa fa-shopping-cart" /> <span className="nav-label">Giỏ hàng</span>
              </Link>
            </li>
            <li className={`${activeMenu === 'order-cart' ? 'active' : ''}`}>
              <Link to={'/order-cart'}>
                <i className="fa fa-files-o" /> <span className="nav-label">Danh sách đơn hàng</span>
              </Link>
            </li>
            <li className={`${activeMenu === 'payment' ? 'active' : ''}`}>
              <Link to={'/payment'}>
                <i className="fa fa-sign-in" /> <span className="nav-label">Ví tiền</span>
              </Link>
            </li>
            {this.userMenu()}
            {this.managerMenu()}
            {this.adminMenu()}
          </ul>
        </div>
      </nav>
    );
  }
}

const mapStateToProps = ({ authentication }) => ({
  account: authentication.account,
  isAdmin: hasAnyAuthority(authentication.account.authorities, [AUTHORITIES.ADMIN]),
  isManager: hasAnyAuthority(authentication.account.authorities, [AUTHORITIES.MANAGER]),
  isStaff: hasAnyAuthority(authentication.account.authorities, [AUTHORITIES.STAFF])
});

const mapDispatchToProps = {};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Sidebar);
