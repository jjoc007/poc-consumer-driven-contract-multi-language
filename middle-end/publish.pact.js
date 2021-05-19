const pact = require('@pact-foundation/pact');

const pactBrokerUrl = 'http://localhost:8000';
const pactBrokerUsername = 'pact_workshop';
const pactBrokerPassword ='pact_workshop';

const opts = {
    pactFilesOrDirs: ['./pacts/'],
    pactBroker: pactBrokerUrl,
    pactBrokerUsername: pactBrokerUsername,
    pactBrokerPassword: pactBrokerPassword,
    tags: ['prod', 'test'],
    consumerVersion: '0.0.1'
};

const pub = new pact.Publisher(opts)
pub.publishPacts()
    .then(() => {
        console.log('Pact contract publishing complete!');
        console.log('');
        console.log(`Head over to ${pactBrokerUrl} and login with`);
        console.log(`=> Username: ${pactBrokerUsername}`);
        console.log(`=> Password: ${pactBrokerPassword}`);
        console.log('to see your published contracts.')
    })
    .catch(e => {
        console.log('Pact contract publishing failed: ', e)
    });
