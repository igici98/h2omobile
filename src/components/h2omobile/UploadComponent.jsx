import React, { Component } from 'react'
import H2oService from "../../api/h2o/H2oService";
import { Field, Formik, Form, ErrorMessage } from 'formik';

//https://www.geeksforgeeks.org/file-uploading-in-react-js/


class UploadComponent extends Component {

    constructor(props) {
        super(props);
        this.state = {
            selectedFile: null

        }



    }


    // On file select (from the pop up)
    onFileChange = event => {

        // Update the state
        this.setState({ selectedFile: event.target.files[0] });
        console.log(this.state.selectedFile);

    };


    // On file upload (click the upload button)
    onFileUpload = () => {

        // Create an object of formData
        const formData = new FormData();

        // Update the formData object
        formData.append(
            "file",
            this.state.selectedFile,
            this.state.selectedFile.name
        );

        // Details of the uploaded file
        console.log(this.state.selectedFile);

        // Request made to the backend api
        // Send formData object
        H2oService.uploadData(formData)
            .then(response => {
                console.log('sucessfull');
            })
            .catch(error => {
                console.log(error);
            });
    };


    render() {



        return (
            <div>
                <div>
                    <h1>Daten hochladen</h1>

                    Hier können Sie neue Daten hochladen! <br />
                    Bitte beachten Sie folgende Reihenfolge beim Hochladen:<br />
                    <br />
                    1. Tabelle: Messstelle<br />
                    2. Tabelle: H2O Probe<br />
                    3. Tabelle: Probedaten<br />
                    < div>  <br /> </div >
                </div>
                <div>

                    <input type="file" onChange={this.onFileChange} />
                    < div>  <br /> </div >
                    <button style={{ marginBottom: `80px` }} onClick={this.onFileUpload} disabled={!this.state.selectedFile}>
                        Upload!
                    </button>
                </div>
            </div >
        )












        /* (






            <div>


                    <div className="container">
                        <Formik

                            initialValues={{ uploadData }}
                            onSubmit={this.onSubmit}


                        >

                            {
                                (props) => (
                                    <Form>

                                        <div>
                                            <h1>Daten hochladen</h1>
                                            < div className="container">
                                                Hier können Sie neue Daten hochladen! <br />
                                                Bitte beachten Sie folgende Reihenfolge beim Hochladen:<br />
                                                <br />
                                                1. Tabelle: Messstelle<br />
                                                2. Tabelle: H2O Probe<br />
                                                3. Tabelle: Probedaten<br />
                                            </div >

                                            < div>  <br /> </div >

                                            <Field as="textarea" style={{ height: '500px', width: '1000px' }} type="text" name="uploadData" rows="4" cols="50" />
                                            < div>  <br /> </div >

                                            <button style={{ marginTop: `20px`, marginBottom: `80px` }} className="btn btn-success" type="submit">Hochladen</button>
                                        </div>


                                    </Form>

                                )
                            }


                        </Formik>
                    </div>

                </div>
                )
                */









    }



}

export default UploadComponent