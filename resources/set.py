import polyglot

class StringWrap:
    def __init__(self, x):
        self.data = x

    def str(self):
        return self.data

def set():
    javaObj = polyglot.import_value('javaObj')
    x_str = StringWrap('set from python')
    k = x_str.str()
    javaObj.setText(k)

set()