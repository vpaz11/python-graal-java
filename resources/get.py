import polyglot

def get():
    javaObj =  polyglot.import_value('javaObj')
    return javaObj.getText()
get()