# Codelab helper

### Setup

- Python

You are required to have python3 if you want to be assisted by the available scripts.
https://www.python.org/downloads/

- claat

Google cli to easily create codelabs using markdown.

https://github.com/googlecodelabs/tools#ok-how-do-i-use-it


### Creating a new codelab

Here is some tooling to help you create and keep the file structure for the codelabs.
In corder to create a new codelab, just to go `codelab/tooling` and use the existing script. Mind that this script is meant to work from the tooling directory.

To create a new codelab from Appyx root folder you can type:

```bash
cd codelabs/tooling
./codelab.py new
```

This will create a new markdown file codelab with an autogenerated id under `codelab/markdown/` folder. It contains a template to ease the process of defining the codelab.

Once it is generated, you can complete the desired metadata info and the codelab content.

If you want check the options you can invoke the script without any argument.

```bash
./codelab.py
```

### Generating files

- Once you are finished with the codelab content, or if want to visualise it. You can generate the html files for the latest codelab using:

```bash
cd codelabs
./codelab.py gen
```

Alternatively, you can specify a codelabId:

```bash
cd codelabs
./codelab.py new 001
```

After doing so, your codelab files will be available in `codelab/html/`

### Update codelab index

You might want to index your codelab to the codelabs doc section, to do so you should edit
`root/documentation/codelabs/index.html`


### Test locally

Just open your codelab html and test that it looks as you intended.
