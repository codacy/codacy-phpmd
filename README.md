[![Codacy Badge](https://api.codacy.com/project/badge/Grade/dbd3ae4c08d24490832393cb2a9474c4)](https://www.codacy.com/gh/codacy/codacy-phpmd?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=codacy/codacy-phpmd&amp;utm_campaign=Badge_Grade)
[![Build Status](https://circleci.com/gh/codacy/codacy-phpmd.svg?style=shield&circle-token=:circle-token)](https://circleci.com/gh/codacy/codacy-phpmd)

# Codacy PHPMD

This is the docker engine we use at Codacy to have [PHPMD](http://phpmd.org/) support.
You can also create a docker to integrate the tool and language of your choice!
See the [codacy-engine-scala-seed](https://github.com/codacy/codacy-engine-scala-seed) repository for more information.

## Usage

You can create the docker by doing:

```
docker build -t codacy-phpmd-base .
sbt docker:publishLocal
```

The docker is ran with the following command:

```
docker run -it -v $srcDir:/src  <DOCKER_NAME>:<DOCKER_VERSION>
```

## Update Documentation

The documentation is updated manually by checking the official documentation rules in the [PHPMD repository](https://github.com/phpmd/phpmd).

## Update version

1. Install php:

```
curl -sS https://getcomposer.org/installer | php (it will create the composer.phar file)
```
2. Update phpmd version on composer.json:

```
"require": {
        "phpmd/phpmd": "x.xx.x"
    }
```

3. Run the update command to get the latest versions of the dependencies and to update the composer.lock file:

```
php composer.phar update
```

Note: If you receive a message like this:

```
Could not fetch <URL package>, please review your configured GitHub OAuth token or enter a new one to access private repos" Create a new token here: https://github.com/settings/tokens/new?scopes=&description=Composer to retrieve a token.
```

create the token and paste it to proceed.

## Test

We use the [codacy-plugins-test](https://github.com/codacy/codacy-plugins-test) to test our external tools integration.
You can follow the instructions there to make sure your tool is working as expected.

## What is Codacy?

[Codacy](https://www.codacy.com/) is an Automated Code Review Tool that monitors your technical debt, helps you improve your code quality, teaches best practices to your developers, and helps you save time in Code Reviews.

### Among Codacy’s features:

- Identify new Static Analysis issues
- Commit and Pull Request Analysis with GitHub, BitBucket/Stash, GitLab (and also direct git repositories)
- Auto-comments on Commits and Pull Requests
- Integrations with Slack, HipChat, Jira, YouTrack
- Track issues in Code Style, Security, Error Proneness, Performance, Unused Code and other categories

Codacy also helps keep track of Code Coverage, Code Duplication, and Code Complexity.

Codacy supports PHP, Python, Ruby, Java, JavaScript, and Scala, among others.

### Free for Open Source

Codacy is free for Open Source projects.
