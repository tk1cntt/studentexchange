import React from 'react';
import { connect } from 'react-redux';
import { NavLink as Link, RouteComponentProps } from 'react-router-dom';
import { Drawer, Menu, Icon } from 'antd';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Translate, translate } from 'react-jhipster';
import { showDrawer } from 'app/shared/reducers/setting';
import { hasAnyAuthority } from 'app/shared/auth/private-route';
import { AUTHORITIES } from 'app/config/constants';

const { SubMenu } = Menu;

export interface ISidebarProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {
  activeMenu: string;
  activeSubMenu: string;
}

export class Sidebar extends React.Component<ISidebarProps> {
  state = {
    width: 0,
    height: 0
  };

  componentDidMount() {
    this.updateDimensions();
    window.addEventListener('resize', this.updateDimensions);
  }

  componentWillUnmount() {
    window.removeEventListener('resize', this.updateDimensions);
  }

  updateDimensions = () => {
    const w = window,
      d = document,
      documentElement = d.documentElement,
      body = d.getElementsByTagName('body')[0],
      width = w.innerWidth || documentElement.clientWidth || body.clientWidth,
      height = w.innerHeight || documentElement.clientHeight || body.clientHeight;
    this.setState({ width, height });
  };

  userMenu() {
    const { activeMenu, activeSubMenu, isUser } = this.props;
    if (!isUser) return '';
    return (
      <>
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
        <li className={`${activeMenu === 'setting' ? 'active' : ''}`}>
          <Link to={'/account/settings'}>
            <i className="fa fa-user" /> <span className="nav-label">Thông tin cá nhân</span>
          </Link>
        </li>
      </>
    );
  }

  userMenuEx() {
    const { activeMenu, activeSubMenu, isUser } = this.props;
    if (!isUser) return '';
    const menus = [];
    menus.push(
      <Menu.Item key="shopping-cart">
        <Link to={'/shopping-cart'}>
          <Icon type="shopping-cart" /> Giỏ hàng
        </Link>
      </Menu.Item>
    );
    menus.push(
      <Menu.Item key="order-cart">
        <Link to={'/order-cart'}>
          <Icon type="bars" />
          Danh sách đơn hàng
        </Link>
      </Menu.Item>
    );
    menus.push(
      <Menu.Item key="payment">
        <Link to={'/payment'}>
          <Icon type="pay-circle" />
          Ví tiền
        </Link>
      </Menu.Item>
    );
    menus.push(
      <Menu.Item key="setting">
        <Link to={'/account/settings'}>
          <Icon type="user" />
          Thông tin cá nhân
        </Link>
      </Menu.Item>
    );
    return menus;
  }

  managerMenu() {
    const { activeMenu, activeSubMenu, isManager } = this.props;
    if (!isManager) return '';
    return (
      <li className={`${activeMenu === 'payment-management' ? 'active' : ''}`}>
        <Link to={'/management/banktransfer'}>
          <i className="fa fa-gift" /> <span className="nav-label">Payment</span> <span className="fa arrow" />
        </Link>
        <ul className={`${activeMenu === 'payment-management' ? 'nav nav-second-level collapse in' : 'nav nav-second-level collapse'}`}>
          <li className={`${activeSubMenu === 'banktransfer' ? 'active' : ''}`}>
            <Link to={'/management/banktransfer'}>
              <i className="fa fa-sign-in" /> Nạp tiền
            </Link>
          </li>
          <li className={`${activeSubMenu === 'history' ? 'active' : ''}`}>
            <Link to={'/management/history'}>
              <i className="fa fa-exchange" /> Lịch sử thanh toán
            </Link>
          </li>
        </ul>
      </li>
    );
  }

  staffMenu() {
    const { activeMenu, activeSubMenu, isStaff } = this.props;
    // const selectedKeys = location.substr(1);
    // const defaultOpenKeys = selectedKeys.split('/')[1];
    if (!isStaff) return '';
    return (
      <li className={`${activeMenu === 'order-management' ? 'active' : ''}`}>
        <Link to={'/staff/order-list'}>
          <i className="fa fa-gift" /> <span className="nav-label">Quản lý đơn hàng</span> <span className="fa arrow" />
        </Link>
        <ul className={`${activeMenu === 'order-management' ? 'nav nav-second-level collapse in' : 'nav nav-second-level collapse'}`}>
          <li className={`${activeSubMenu === 'order-list' ? 'active' : ''}`}>
            <Link to={'/staff/order-list'}>
              <i className="fa fa-list" /> Danh sách đơn hàng
            </Link>
          </li>
          <li className={`${activeSubMenu === 'order-deposited' ? 'active' : ''}`}>
            <Link to={'/staff/order-deposited'}>
              <i className="fa fa-sign-in" /> Đơn hàng chưa xử lý
            </Link>
          </li>
          <li className={`${activeSubMenu === 'order-buying' ? 'active' : ''}`}>
            <Link to={'/staff/order-buying'}>
              <i className="fa fa-exchange" /> Đơn hàng bạn đã nhận
            </Link>
          </li>
          <li className={`${activeSubMenu === 'order-purchased' ? 'active' : ''}`}>
            <Link to={'/staff/order-purchased'}>
              <i className="fa fa-check-circle" /> Đơn hàng đã xử lý
            </Link>
          </li>
          <li className={`${activeSubMenu === 'order-cancel' ? 'active' : ''}`}>
            <Link to={'/staff/order-cancel'}>
              <i className="fa fa-window-close" /> Đơn hàng đã huỷ
            </Link>
          </li>
        </ul>
      </li>
    );
  }

  staffMenuEx() {
    const { activeMenu, activeSubMenu, isStaff } = this.props;
    // const selectedKeys = location.substr(1);
    // const defaultOpenKeys = selectedKeys.split('/')[1];
    if (!isStaff) return '';
    return (
      <SubMenu
        key="staff"
        title={
          <span>
            <Icon type="setting" />
            <span>Quản lý đơn hàng</span>
          </span>
        }
      >
        <Menu.Item key="order-list">
          <Link to={'/staff/order-list'}>
            <i className="fa fa-list" /> Danh sách đơn hàng
          </Link>
        </Menu.Item>
        <Menu.Item key="order-deposited">
          <Link to={'/staff/order-deposited'}>
            <i className="fa fa-sign-in" /> Đơn hàng chưa xử lý
          </Link>
        </Menu.Item>
        <Menu.Item key="order-buying">
          <Link to={'/staff/order-buying'}>
            <i className="fa fa-exchange" /> Đơn hàng bạn đã nhận
          </Link>
        </Menu.Item>
        <Menu.Item key="order-purchased">
          <Link to={'/staff/order-purchased'}>
            <i className="fa fa-check-circle" /> Đơn hàng đã xử lý
          </Link>
        </Menu.Item>
        <Menu.Item key="order-cancel">
          <Link to={'/staff/order-cancel'}>
            <i className="fa fa-window-close" /> Đơn hàng đã huỷ
          </Link>
        </Menu.Item>
      </SubMenu>
    );
  }

  logisticsMenu() {
    const { activeMenu, activeSubMenu, isStaff } = this.props;
    if (!isStaff) return '';
    return (
      <li className={`${activeMenu === 'order-management' ? 'active' : ''}`}>
        <Link to={'/staff/order-deposited'}>
          <i className="fa fa-gift" /> <span className="nav-label">Quản lý vận chuyển hàng</span> <span className="fa arrow" />
        </Link>
        <ul className={`${activeMenu === 'order-management' ? 'nav nav-second-level collapse in' : 'nav nav-second-level collapse'}`}>
          <li className={`${activeSubMenu === 'order-deposited' ? 'active' : ''}`}>
            <Link to={'/staff/order-deposited'}>
              <i className="fa fa-sign-in" /> Nhập hàng ở kho Trung Quốc
            </Link>
          </li>
          <li className={`${activeSubMenu === 'order-buying' ? 'active' : ''}`}>
            <Link to={'/staff/order-buying'}>
              <i className="fa fa-exchange" /> Đóng bao
            </Link>
          </li>
          <li className={`${activeSubMenu === 'order-purchased' ? 'active' : ''}`}>
            <Link to={'/staff/order-purchased'}>
              <i className="fa fa-check-circle" /> Xuất hàng kho Trung Quốc
            </Link>
          </li>
          <li className={`${activeSubMenu === 'order-purchased' ? 'active' : ''}`}>
            <Link to={'/staff/order-purchased'}>
              <i className="fa fa-check-circle" /> Nhập hàng kho Việt Nam
            </Link>
          </li>
          <li className={`${activeSubMenu === 'order-purchased' ? 'active' : ''}`}>
            <Link to={'/staff/order-purchased'}>
              <i className="fa fa-check-circle" /> Đơn hàng yêu cầu giao
            </Link>
          </li>
          <li className={`${activeSubMenu === 'order-purchased' ? 'active' : ''}`}>
            <Link to={'/staff/order-purchased'}>
              <i className="fa fa-check-circle" /> Giao hàng
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

  adminMenuEx() {
    const { activeMenu, activeSubMenu, isAdmin } = this.props;
    if (!isAdmin) return '';
    return (
      <SubMenu
        key="admin"
        title={
          <span>
            <Icon type="setting" />
            <span>Administration</span>
          </span>
        }
      >
        <Menu.Item key="user-management">
          <Link to={'/admin/user-management'}>
            <FontAwesomeIcon icon="user" fixedWidth />
            Users
          </Link>
        </Menu.Item>
        <Menu.Item key="metrics">
          <Link to={'/admin/metrics'}>
            <FontAwesomeIcon icon="tachometer-alt" fixedWidth /> Metrics
          </Link>
        </Menu.Item>
        <Menu.Item key="health">
          <Link to={'/admin/health'}>
            <FontAwesomeIcon icon="heart" fixedWidth /> Health
          </Link>
        </Menu.Item>
        <Menu.Item key="configuration">
          <Link to={'/admin/configuration'}>
            <FontAwesomeIcon icon="list" fixedWidth /> Configuration
          </Link>
        </Menu.Item>
        <Menu.Item key="audits">
          <Link to={'/admin/audits'}>
            <FontAwesomeIcon icon="bell" fixedWidth /> Audits
          </Link>
        </Menu.Item>
        <Menu.Item key="logs">
          <Link to={'/admin/logs'}>
            <FontAwesomeIcon icon="tasks" fixedWidth /> Logs
          </Link>
        </Menu.Item>
      </SubMenu>
    );
  }

  onClose = () => {
    this.props.showDrawer(false);
  };

  showDrawerMenu() {
    return (
      <Drawer width={220} placement="left" closable={false} onClose={this.onClose} visible={this.props.setting.showDrawer}>
        {this.showNormalMenu()}
      </Drawer>
    );
  }

  showNormalMenu() {
    return (
      <>
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
        <Menu mode="inline" theme="dark" style={{ width: 220 }} defaultSelectedKeys={['4']}>
          <Menu.Item key="0">
            <Icon type="appstore" />
            Thông tin chung
          </Menu.Item>
          {this.userMenuEx()}
          {this.staffMenuEx()}
          {this.adminMenuEx()}
          <SubMenu
            key="sub1"
            title={
              <span>
                <Icon type="mail" />
                <span>Navigation One</span>
              </span>
            }
          >
            <Menu.Item key="1">Option 1</Menu.Item>
            <Menu.Item key="2">Option 2</Menu.Item>
            <Menu.Item key="3">Option 3</Menu.Item>
            <Menu.Item key="4">Option 4</Menu.Item>
          </SubMenu>
          <SubMenu
            key="sub2"
            title={
              <span>
                <Icon type="appstore" />
                <span>Navigation Two</span>
              </span>
            }
          >
            <Menu.Item key="5">Option 5</Menu.Item>
            <Menu.Item key="6">Option 6</Menu.Item>
            <SubMenu key="sub3" title="Submenu">
              <Menu.Item key="7">Option 7</Menu.Item>
              <Menu.Item key="8">Option 8</Menu.Item>
            </SubMenu>
          </SubMenu>
          <SubMenu
            key="sub4"
            title={
              <span>
                <Icon type="setting" />
                <span>Navigation Three</span>
              </span>
            }
          >
            <Menu.Item key="9">Option 9</Menu.Item>
            <Menu.Item key="10">Option 10</Menu.Item>
            <Menu.Item key="11">Option 11</Menu.Item>
            <Menu.Item key="12">Option 12</Menu.Item>
          </SubMenu>
        </Menu>
      </>
    );
  }

  render() {
    const { activeMenu, activeSubMenu, location } = this.props;
    console.log('>>>>>>>>>>>', location);
    // if (isAuthenticated !== true) return (<div />);
    return (
      <nav className="navbar-default navbar-static-side" role="navigation">
        <div className="sidebar-collapse">
          <ul className="nav metismenu" id="side-menu">
            {this.state.width > 768 ? this.showNormalMenu() : this.showDrawerMenu()}
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
            {this.userMenu()}
            {this.managerMenu()}
            {this.staffMenu()}
            {this.adminMenu()}
          </ul>
        </div>
      </nav>
    );
  }
}

const mapStateToProps = ({ authentication, setting }) => ({
  account: authentication.account,
  setting,
  isAdmin: hasAnyAuthority(authentication.account.authorities, [AUTHORITIES.ADMIN]),
  isManager: hasAnyAuthority(authentication.account.authorities, [AUTHORITIES.MANAGER]),
  isStaff: hasAnyAuthority(authentication.account.authorities, [AUTHORITIES.STAFF]),
  isUser: hasAnyAuthority(authentication.account.authorities, [AUTHORITIES.USER])
});

const mapDispatchToProps = { showDrawer };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Sidebar);
