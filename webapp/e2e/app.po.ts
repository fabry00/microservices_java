export class WebappPage {
  navigateTo() {
    return browser.get('/');
  }

  getParagraphText() {
    return element(by.css('webapp-app h1')).getText();
  }
}
